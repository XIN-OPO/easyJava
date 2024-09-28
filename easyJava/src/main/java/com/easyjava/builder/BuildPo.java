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
import java.nio.charset.Charset;
import java.util.Date;

public class BuildPo {
    private static final Logger logger= LoggerFactory.getLogger(BuildPo.class);
    public static void execute(TableInfo tableInfo){
        File folder=new File(Constants.path_po);
        if(!folder.exists()){
            folder.mkdirs();
        }
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        File poFile=new File(folder,tableInfo.getBeanName()+".java");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(poFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.package_po+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import java.io.Serializable;");
            bufferedWriter.newLine();
            if(tableInfo.isHaveDateTime()|| tableInfo.isHaveDate()){
                bufferedWriter.write("import java.util.Date;");
                bufferedWriter.newLine();
                bufferedWriter.write(Constants.bean_date_format_class+";");
                bufferedWriter.newLine();
                bufferedWriter.write(Constants.bean_date_unformat_class+";");
                bufferedWriter.newLine();
                bufferedWriter.write("import "+Constants.package_enums+".DateTimePatternEnum;");
                bufferedWriter.newLine();
                bufferedWriter.write("import "+Constants.package_utils+".DateUtils;");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            if(tableInfo.isHaveBigDecimal()){
                bufferedWriter.write("import java.math.BigDecimal;");
                bufferedWriter.newLine();
            }

            Boolean haveIngnoreBean=false;
            for(FieldInfo fieldInfo: tableInfo.getFieldList()){
                if(ArrayUtils.contains(Constants.ignore_bean_tojson_filed.split(","),fieldInfo.getPropertyName())){
                    haveIngnoreBean=true;
                    break;
                }
            }
            if(haveIngnoreBean){
                bufferedWriter.write(Constants.ignore_bean_tojson_class+";");
                bufferedWriter.newLine();
            }
            bufferedWriter.write("public class "+tableInfo.getBeanName()+" implements Serializable {");
            bufferedWriter.newLine();

            //构建类注释
            BuildComment.creatClassComment(bufferedWriter, tableInfo.getComment());
            //构建字段以及字段注释
            for(FieldInfo fieldInfo: tableInfo.getFieldList()){
                BuildComment.createFieldComment(bufferedWriter,fieldInfo.getComment());
                if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,fieldInfo.getSqlType())){
                    bufferedWriter.write("\t"+String.format(Constants.bean_date_format_expression, DateUtils.YYYY_MM_DD_HH_mm_SS));
                    bufferedWriter.newLine();
                    bufferedWriter.write("\t"+String.format(Constants.bean_date_unformat_expression, DateUtils.YYYY_MM_DD_HH_mm_SS));
                    bufferedWriter.newLine();
                }
                if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,fieldInfo.getSqlType())){
                    bufferedWriter.write("\t"+String.format(Constants.bean_date_format_expression, DateUtils.YYYY_MM_DD));
                    bufferedWriter.newLine();
                    bufferedWriter.write("\t"+String.format(Constants.bean_date_unformat_expression, DateUtils.YYYY_MM_DD));
                    bufferedWriter.newLine();
                }

                if(ArrayUtils.contains(Constants.ignore_bean_tojson_filed.split(","),fieldInfo.getPropertyName())){
                    bufferedWriter.write("\t"+ Constants.ignore_bean_tojson_expression);
                    bufferedWriter.newLine();
                }

                bufferedWriter.write("\tprivate "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName()+";");
                bufferedWriter.newLine();
                bufferedWriter.newLine();
            }
            //get set方法
            for(FieldInfo fieldInfo: tableInfo.getFieldList()){
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

            StringBuffer toString=new StringBuffer();
            Integer index=0;
            //重写toString方法
            for(FieldInfo fieldInfo: tableInfo.getFieldList()){
                index++;
                String properName= fieldInfo.getPropertyName();
                if(ArrayUtils.contains(Constants.SQL_DATE_TIME_TYPES,fieldInfo.getSqlType())){
                    properName="DateUtils.format("+properName+", DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())";
                }else if(ArrayUtils.contains(Constants.SQL_DATE_TYPES,fieldInfo.getSqlType())){
                    properName="DateUtils.format("+properName+", DateTimePatternEnum.YYYY_MM_DD.getPattern())";
                }
                toString.append(fieldInfo.getFieldName()+":\"+("+properName+" == null ? \"空\" : "+fieldInfo.getPropertyName()+")");
                if(index<tableInfo.getFieldList().size()){
                    toString.append("+").append("\",");
                }
//                toString.append("\",");
            }
            String toStringStr= String.valueOf(toString);
            toStringStr="\""+toStringStr;
            //toStringStr.substring(0,toStringStr.lastIndexOf(","));

            bufferedWriter.write("\t@Override");
            bufferedWriter.newLine();
            bufferedWriter.write("\t public String toString(){");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn "+toStringStr+";");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();

            bufferedWriter.write("}");
            bufferedWriter.flush();
        }catch (Exception e){
            logger.info("创建po失败",e);
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
