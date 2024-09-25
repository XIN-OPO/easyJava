package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.baen.FieldInfo;
import com.easyjava.baen.TableInfo;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    private static final Logger logger= LoggerFactory.getLogger(BuildMapperXml.class);
    private static final String base_column_list="base_column_list";
    private static final String base_query_condition="base_query_condition";
    private static final String query_condition ="query_condition";
    private static final String base_query_condition_extend="base_query_condition_extend";
    public static void execute(TableInfo tableInfo) {
        File folder = new File(Constants.path_mapper_xmls);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        String className = tableInfo.getBeanName() + Constants.suffix_mappers;
        File poFile = new File(folder, className + ".xml");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(poFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);

            bufferedWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write("<!DOCTYPE mapper PUBLIC \"-//mybatis.org//DTD Mapper 3.0//EN\"");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\"http://mybatis.org/dtd/mybatis-3-mapper.dtd\">");
            bufferedWriter.newLine();
            bufferedWriter.write("<mapper namespace=\""+Constants.package_mappers+"."+className+"\">");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<!-- 实体映射 -->");
            bufferedWriter.newLine();

            String poClass=Constants.package_po+"."+tableInfo.getBeanName();
            bufferedWriter.write("\t<resultMap id=\"base_result_map\" type=\""+poClass+"\">");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            FieldInfo fieldId=null;
            Map<String,List<FieldInfo>> keyIndexMap= tableInfo.getKeyIndexMap();
            for(Map.Entry<String,List<FieldInfo>> entry: keyIndexMap.entrySet()){
                if("PRIMARY".equals(entry.getKey())){
                    List<FieldInfo> fieldInfoList=entry.getValue();
                    if(fieldInfoList.size()==1){
                        fieldId=fieldInfoList.get(0);
                        break;
                    }
                }
            }

            for(FieldInfo fieldInfo:tableInfo.getFieldList()){
                bufferedWriter.write("\t\t<!--" +fieldInfo.getComment()+" -->");
                bufferedWriter.newLine();
                String key;
                if(fieldId!=null && fieldInfo.getPropertyName().equals(fieldId.getPropertyName())){
                    //如果只有一个
                    key="id";
                }else{
                    key="result";
                }
                bufferedWriter.write("\t\t<"+key+" column=\""+fieldInfo.getFieldName()+"\" property=\""+ fieldInfo.getPropertyName()+"\"/>");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("\t</resultMap>");
            bufferedWriter.newLine();

            //通用查询列
            bufferedWriter.write("\t<!-- 通用查询结果列 -->");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<sql id=\""+base_column_list+"\">");
            bufferedWriter.newLine();
            StringBuilder columnBuilder=new StringBuilder();
            for (FieldInfo fieldInfo: tableInfo.getFieldList()){
                columnBuilder.append(fieldInfo.getFieldName()).append(",");
            }
            String columnBuilderStr=columnBuilder.substring(0, columnBuilder.lastIndexOf(","));
//            bufferedWriter.write("\t\t<if test=\"true\">");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+columnBuilderStr);
            bufferedWriter.newLine();
//            bufferedWriter.write("\t\t</if>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t</sql>");
            bufferedWriter.newLine();


            //基础查询条件
            bufferedWriter.write("\t<!-- 基础查询条件 -->");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<sql id=\""+ base_query_condition +"\">");
            bufferedWriter.newLine();
//            StringBuilder columnBuilder=new StringBuilder();

            for (FieldInfo fieldInfo: tableInfo.getFieldList()){
                String stringQuery="";
                if(ArrayUtils.contains(Constants.SQL_STRING_TYPES,fieldInfo.getSqlType())){
                    stringQuery=" and query."+fieldInfo.getPropertyName()+"!=''";
                }
                bufferedWriter.write("\t\t<if test=\"query."+fieldInfo.getPropertyName()+" !=null"+stringQuery+"\">");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t\tand id= #{query."+fieldInfo.getPropertyName()+"}");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t</if>");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("\t</sql>");
            bufferedWriter.newLine();

            //扩展的查询条件
            bufferedWriter.write("\t<!-- 扩展查询条件 -->");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<sql id=\""+ base_query_condition_extend +"\">");
            bufferedWriter.newLine();
            for (FieldInfo fieldInfo: tableInfo.getFieldExtendList()){
//                String stringQuery="";
                String andWhere="";
                if(ArrayUtils.contains(Constants.SQL_STRING_TYPES,fieldInfo.getSqlType())){
//                    stringQuery=" and query."+fieldInfo.getPropertyName()+"!=''";
                    andWhere="and "+fieldInfo.getFieldName()+" like concat('%',#{query."+fieldInfo.getPropertyName()+"},'%')";
                }else if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,fieldInfo.getSqlType())||ArrayUtils.contains(Constants.SQL_DATE_TYPES,fieldInfo.getSqlType())){
                    if(fieldInfo.getPropertyName().endsWith(Constants.suffix_bean_query_time_start)) {
                        andWhere = "<![CDATA[ and " + fieldInfo.getFieldName() + " >= str_to_date(#{query." + fieldInfo.getPropertyName() + "}, '%Y-%m-%d') ]]>";
                    }else if(fieldInfo.getPropertyName().endsWith(Constants.suffix_bean_query_time_end)){
                        andWhere="<![CDATA[ and "+fieldInfo.getFieldName()+" < date_sub(str_to_date({#query."+fieldInfo.getPropertyName()+"}, '%Y-%m-%d'),interval -1 day) ]]>";
                    }
                }
                bufferedWriter.write("\t\t<if test=\"query."+fieldInfo.getPropertyName()+" !=null and query."+fieldInfo.getPropertyName()+" !=''\">");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t\t"+andWhere);
                bufferedWriter.newLine();
                bufferedWriter.write("\t\t</if>");
                bufferedWriter.newLine();
            }


            bufferedWriter.write("\t</sql>");
            bufferedWriter.newLine();

            //通用的查询条件
            bufferedWriter.write("\t<!-- 通用查询条件 -->");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<sql id=\""+query_condition +"\">");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t<where>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t<include refid=\""+base_query_condition+"\"/>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t<include refid=\""+base_query_condition_extend+"\"/>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t</where>");
            bufferedWriter.newLine();

            bufferedWriter.write("\t</sql>");
            bufferedWriter.newLine();

            //查询列表
            bufferedWriter.write("\t<!-- 查询列表 -->");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<select id=\"selectList\" resultMap=\"base_result_map\">");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tSELECT <include refid=\""+base_column_list+"\"/> FROM "+tableInfo.getTableName()+" <include refid=\""+query_condition+"\"/>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t<if test=\"query.orderBy!=null\">order by ${query.orderBy}</if>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t<if test=\"query.simplePage!=null\">limit #{query.simplePage.start},#{query.simplePage.end}</if>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t</select>");
            bufferedWriter.newLine();

            //查询数量
            bufferedWriter.write("\t<!-- 查询数量 -->");
            bufferedWriter.newLine();
            bufferedWriter.write("\t<select id=\"selectCount\" resultType=\"java.lang.Long\">");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tSELECT count(1) FROM "+tableInfo.getTableName()+" <include refid=\""+query_condition+"\"/>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t</select>");
            bufferedWriter.newLine();


            bufferedWriter.write("</mapper>");

            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception e){
            logger.info("创建mapper xml失败",e);
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
}
