package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.baen.FieldInfo;
import com.easyjava.baen.TableInfo;
import com.easyjava.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildMapperXml {
    private static final Logger logger= LoggerFactory.getLogger(BuildMapperXml.class);
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
            String base_column_list="base_column_list";
            bufferedWriter.write("\t<sql id=\""+base_column_list+"\">");
            bufferedWriter.newLine();
            StringBuilder columnBuilder=new StringBuilder();
            for (FieldInfo fieldInfo: tableInfo.getFieldList()){
                columnBuilder.append(fieldInfo.getFieldName()).append(",");
            }
            String columnBuilderStr=columnBuilder.substring(0, columnBuilder.lastIndexOf(","));
            bufferedWriter.write("\t\t<if test=\"true\">");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\t"+columnBuilderStr);
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t</if>");
            bufferedWriter.newLine();
            bufferedWriter.write("\t</sql>");
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
