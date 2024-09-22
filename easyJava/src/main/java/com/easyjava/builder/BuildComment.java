package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.baen.FieldInfo;
import com.easyjava.utils.DateUtils;

import java.io.BufferedWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.SimpleFormatter;

public class BuildComment {
    public static void creatClassComment(BufferedWriter bufferedWriter,String classComment) throws Exception{
        bufferedWriter.write("/**");
        bufferedWriter.newLine();
        bufferedWriter.write("*@Description: "+ classComment);
        bufferedWriter.newLine();
        bufferedWriter.write("*@Author: "+ Constants.author_comment);
        bufferedWriter.newLine();
        bufferedWriter.write("*@Date: "+ DateUtils.format(new Date(),DateUtils._YYYYMMDD));
        bufferedWriter.newLine();
        bufferedWriter.write("*/");
        bufferedWriter.newLine();
    }
    public static void createFieldComment(BufferedWriter bufferedWriter, String fieldComment) throws Exception{
        if(fieldComment.length()!=0){//有注解才添加注解
            bufferedWriter.write("/**");
            bufferedWriter.newLine();
            bufferedWriter.write(" *"+fieldComment);
            bufferedWriter.newLine();
            bufferedWriter.write("*/");
            bufferedWriter.newLine();
        }
    }
    public static void creatMethodComment(){

    }
}


