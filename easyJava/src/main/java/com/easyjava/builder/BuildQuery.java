package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.baen.FieldInfo;
import com.easyjava.baen.TableInfo;
import com.easyjava.utils.DateUtils;
import com.easyjava.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BuildQuery {
    private static final Logger logger= LoggerFactory.getLogger(BuildQuery.class);
    public static void execute(TableInfo tableInfo){
        File folder=new File(Constants.path_query);
        if(!folder.exists()){
            folder.mkdirs();
        }
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        String className=tableInfo.getBeanName()+Constants.suffix_bean_query;
        File queryFile=new File(folder,className+".java");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(queryFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.package_query+";");
            bufferedWriter.newLine();

            if(tableInfo.isHaveDateTime()|| tableInfo.isHaveDate()){
                bufferedWriter.write("import java.util.Date;");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            if(tableInfo.isHaveBigDecimal()){
                bufferedWriter.write("import java.math.BigDecimal;");
                bufferedWriter.newLine();
            }


            bufferedWriter.write("public class "+className+" {");
            bufferedWriter.newLine();

            //构建类注释
            BuildComment.creatClassComment(bufferedWriter, tableInfo.getComment()+"查询对象");
            //构建字段以及字段注释
            List<FieldInfo> extendList=new ArrayList();
            for(FieldInfo fieldInfo: tableInfo.getFieldList()){
                BuildComment.createFieldComment(bufferedWriter,fieldInfo.getComment());

                bufferedWriter.write("\tprivate "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
                //String类型的参数
                if(ArrayUtils.contains(Constants.SQL_STRING_TYPES,fieldInfo.getSqlType())){
                    String propertyName=fieldInfo.getPropertyName()+Constants.suffix_bean_query_fuzzy;
                    bufferedWriter.write("\tprivate "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+Constants.suffix_bean_query_fuzzy+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();

                    FieldInfo fuzzyField=new FieldInfo();
                    fuzzyField.setJavaType(fieldInfo.getJavaType());
                    fuzzyField.setPropertyName(propertyName);
                    extendList.add(fuzzyField);
                }
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,fieldInfo.getSqlType())||ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,fieldInfo.getSqlType())){
                    bufferedWriter.write("\tprivate String"+" "+fieldInfo.getPropertyName()+Constants.suffix_bean_query_time_start+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();

                    bufferedWriter.write("\tprivate String"+" "+fieldInfo.getPropertyName()+Constants.suffix_bean_query_time_end+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();

                    FieldInfo timeStartField=new FieldInfo();
                    timeStartField.setJavaType("String");
                    timeStartField.setPropertyName(fieldInfo.getPropertyName()+Constants.suffix_bean_query_time_start);
                    extendList.add(timeStartField);

                    FieldInfo timeEndField=new FieldInfo();
                    timeEndField.setJavaType("String");
                    timeEndField.setPropertyName(fieldInfo.getPropertyName()+Constants.suffix_bean_query_time_end);
                    extendList.add(timeEndField);
                }
            }
            List<FieldInfo> fieldInfoList= tableInfo.getFieldList();
//            fieldInfoList.addAll(extendList);
            //get set方法
            buildGetSet(bufferedWriter,fieldInfoList);
            buildGetSet(bufferedWriter,extendList);
            bufferedWriter.write("}");
            bufferedWriter.flush();
        }catch (Exception e){
            logger.info("创建query失败",e);
        }finally {
            if(bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStreamWriter!=null){
                try {
                    outputStreamWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private static void buildGetSet(BufferedWriter bufferedWriter,List<FieldInfo> fieldInfoList) throws Exception{
        for(FieldInfo fieldInfo: fieldInfoList){
            String tempField= StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName());
            bufferedWriter.write("\t public void set"+tempField+"("+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+"){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tthis."+fieldInfo.getPropertyName()+"="+fieldInfo.getPropertyName()+";");
            bufferedWriter.newLine();
            bufferedWriter.write("\t }");
            bufferedWriter.newLine();

            bufferedWriter.write("\t public "+fieldInfo.getJavaType()+" get"+tempField+"(){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn this."+fieldInfo.getPropertyName()+";");
            bufferedWriter.newLine();
            bufferedWriter.write("\t }");
            bufferedWriter.newLine();
        }
    }
}
