package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.baen.FieldInfo;
import com.easyjava.baen.TableInfo;
import com.easyjava.utils.JsonUtils;
import com.easyjava.utils.PropertiesUtils;
import com.easyjava.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.spi.DirectoryManager;
import java.sql.*;
import java.util.*;

public class BuildTable {
    private static final Logger logger= LoggerFactory.getLogger(BuildTable.class);
    private static Connection connection=null;
    //得到数据表结构所需的信息的sql语句
    private static String SQL_SHOW_TABLE_STATUS="show table status";
    private static String SQL_SHOW_TABLE_FIELDS="show full fields from %s ";
    private static String SQL_SHOW_TABLE_INDEX="show index from %s";
    static {
        String driverName= PropertiesUtils.getString("db.driver.name");
        String url=PropertiesUtils.getString("db.url");
        String name=PropertiesUtils.getString("db.username");
        String password=PropertiesUtils.getString("db.password");
        try {
            Class.forName(driverName);
            connection= DriverManager.getConnection(url,name,password);
        } catch (Exception e) {
            logger.error("数据库连接失败",e);
        }
    }
    public static List<TableInfo> getTables(){
        PreparedStatement preparedStatement=null;
        ResultSet tableResult=null;
        List<TableInfo> tableInfoList=new ArrayList();
        try {
            preparedStatement=connection.prepareStatement(SQL_SHOW_TABLE_STATUS);
            tableResult=preparedStatement.executeQuery();
            while (tableResult.next()){
                String tableName=tableResult.getString("Name");
                String comment=tableResult.getString("Comment");
//                logger.info("tableName:{},comment:{}",tableName,comment);
                TableInfo tableInfo=new TableInfo();
                tableInfo.setTableName(tableName);
                String beanName=tableName;
                //如果需要忽略前缀则进行处理
                if(Constants.ignore_table_prefix){
                    beanName=tableName.substring(beanName.indexOf("_")+1);
                }
//                logger.info(beanName);
                beanName=processField(beanName,true);
//                logger.info(beanName);
                tableInfo.setBeanName(beanName);
                tableInfo.setComment(comment);
                tableInfo.setBeanParamName(beanName+Constants.suffix_bean_query);
//                logger.info("表:{},备注:{},JavaBean:{},JavaBeanParam:{}",tableName,tableInfo.getComment(),tableInfo.getBeanName(),tableInfo.getBeanParamName());
                List<FieldInfo> fieldInfoList=readFieldInfo(tableInfo);
//                logger.info("表{}",JsonUtils.convertObj2Json(tableInfo));
//                logger.info("字段{}",JsonUtils.convertObj2Json(fieldInfoList));
                getKeyIndexInfo(tableInfo);
//                logger.info("tableInfo:{}",JsonUtils.convertObj2Json(tableInfo));
                tableInfoList.add(tableInfo);
            }
        }catch (Exception e){
            logger.error("读取表失败");
        }finally {
            if(tableResult!=null){
                try {
                    tableResult.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection!=null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return tableInfoList;
    }

    //处理字段
    private static String processField(String field,Boolean upperCaseFirstLetter){
        StringBuffer sb=new StringBuffer();
        String[] fields=field.split("_");
        sb.append(upperCaseFirstLetter? StringUtils.upperCaseFirstLetter(fields[0]) :fields[0]);
        for (int i=1,len=fields.length;i<len;i++){
            sb.append(StringUtils.upperCaseFirstLetter(fields[i]));
        }
        return sb.toString();
    }
    private static List<FieldInfo> readFieldInfo(TableInfo tableInfo){
        PreparedStatement preparedStatement=null;
        ResultSet fieldResult=null;
        List<FieldInfo> fieldInfoList=new ArrayList();
        try {
            preparedStatement=connection.prepareStatement(String.format(SQL_SHOW_TABLE_FIELDS,tableInfo.getTableName()));
            fieldResult=preparedStatement.executeQuery();
            Boolean haveDate=false;
            Boolean haveDateTime=false;
            Boolean haveBigDecimal=false;
            while (fieldResult.next()){
                String field=fieldResult.getString("field");
                String type=fieldResult.getString("type");
                String extra=fieldResult.getString("extra");
                String comment=fieldResult.getString("comment");

                //不需要varchar中的括号
                if(type.indexOf("(")>0){
                    type=type.substring(0,type.indexOf("("));
                }

                String propertyName=processField(field,false);
                FieldInfo fieldInfo=new FieldInfo();
                fieldInfoList.add(fieldInfo);

                fieldInfo.setFieldName(field);
                fieldInfo.setSqlType(type);
                fieldInfo.setComment(comment);
                fieldInfo.setAutoIncrement("auto_increment".equalsIgnoreCase(extra)?true:false);
                fieldInfo.setPropertyName(propertyName);
                fieldInfo.setJavaType(processJavaType(type));
//                logger.info("field:{},type:{},extra:{},comment:{}",field,type,extra,comment);
//                logger.info(fieldInfo.getJavaType());
                if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type)){
                    haveDateTime=true;
                }

                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,type)){
                    haveDate=true;
                }
                if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES,type)){
                    haveBigDecimal=true;
                }


            }
            tableInfo.setHaveDateTime(haveDateTime);
            tableInfo.setHaveDate(haveDate);
            tableInfo.setHaveBigDecimal(haveBigDecimal);
            tableInfo.setFieldList(fieldInfoList);
        }catch (Exception e){
            logger.error("读取表失败");
        }finally {
            if(fieldResult!=null){
                try {
                    fieldResult.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return fieldInfoList;
    }
    private static List<FieldInfo> getKeyIndexInfo(TableInfo tableInfo){
        PreparedStatement preparedStatement=null;
        ResultSet fieldResult=null;
        List<FieldInfo> fieldInfoList=new ArrayList();
        try{
            Map<String,FieldInfo> tempMap=new HashMap();
            for(FieldInfo fieldInfo:tableInfo.getFieldList()) {
                    tempMap.put(fieldInfo.getFieldName(),fieldInfo);
            }
            preparedStatement=connection.prepareStatement(String.format(SQL_SHOW_TABLE_INDEX,tableInfo.getTableName()));
            fieldResult=preparedStatement.executeQuery();
            while (fieldResult.next()){
                String keyName=fieldResult.getString("key_name");
                Integer nonUnique=fieldResult.getInt("non_unique");
                String columnName=fieldResult.getString("column_name");
                if(nonUnique==1){
                    continue;
                }
                List<FieldInfo> keyFieldList = tableInfo.getKeyIndexMap().get(keyName);
                if(null==keyFieldList){
                    keyFieldList=new ArrayList();
                    tableInfo.getKeyIndexMap().put(keyName,keyFieldList);
                }
                keyFieldList.add(tempMap.get(columnName));
            }
        }catch (Exception e){
//            e.printStackTrace();
            logger.error("读取索引失败");
        }finally {
            if(fieldResult!=null){
                try {
                    fieldResult.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(preparedStatement!=null){
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return fieldInfoList;
    }
    private static String processJavaType(String type){
        if(ArrayUtils.contains(Constants.SQL_INTEGER_TYPES,type)){
            return "Integer";
        }else if(ArrayUtils.contains(Constants.SQL_DECIMAL_TYPES,type)){
            return "BigDecimal";
        }else if(ArrayUtils.contains(Constants.SQL_LONG_TYPES,type)){
            return "Long";
        }else if(ArrayUtils.contains(Constants.SQL_STRING_TYPES,type)){
            return "String";
        }else if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,type)||ArrayUtils.contains(Constants.SQL_DATE_TYPES,type)){
            return "Date";
        }else{
            throw new RuntimeException("无法识别类型"+type);
        }
    }
}
