package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//创造基础类
public class BuildBase {

        private static Logger logger=LoggerFactory.getLogger(BuildBase.class);
        public static void execute(){
            List<String> headerInfoList=new ArrayList();
            //生成date枚举
            headerInfoList.add("package "+Constants.package_enums);
            build(headerInfoList,"DateTimePatternEnum", Constants.path_enums);

            headerInfoList.clear();
            headerInfoList.add("package "+Constants.package_utils);
            build(headerInfoList,"DateUtils", Constants.path_utils);

            //生成BaseMapper
            headerInfoList.clear();
            headerInfoList.add("package "+Constants.package_mappers);
            build(headerInfoList,"BaseMapper",Constants.path_mappers);

            //生成pageSize枚举
            headerInfoList.clear();
            headerInfoList.add("package "+Constants.package_enums);
            build(headerInfoList,"PageSize",Constants.path_enums);

            //生成SimplePage
            headerInfoList.clear();
            headerInfoList.add("package "+Constants.package_query);
            headerInfoList.add("import "+Constants.package_enums+".PageSize");

            build(headerInfoList,"SimplePage",Constants.path_query);

            //生成BaseQuery
            headerInfoList.clear();
            headerInfoList.add("package "+Constants.package_query);
            build(headerInfoList,"BaseParam",Constants.path_query);

        }

        private static void build(List<String> headerInfoList, String fileName, String outputPath) {
            File folder=new File(outputPath);
            if(!folder.exists()){
                folder.mkdirs();
            }

            File javaFile=new File(outputPath,fileName+".java");
            OutputStream out=null;
            OutputStreamWriter outw=null;
            BufferedWriter bufferedWriter=null;

            InputStream inputStream=null;
            InputStreamReader inputStreamReader=null;
            BufferedReader bufferedReader=null;
            try {
                out=new FileOutputStream(javaFile);
                outw=new OutputStreamWriter(out,"utf8");
                bufferedWriter=new BufferedWriter(outw);

                String templatePath=BuildBase.class.getClassLoader().getResource("template/"+fileName+".txt").getPath();
                inputStream=new FileInputStream(templatePath);
                inputStreamReader=new InputStreamReader(inputStream,"utf8");
                bufferedReader=new BufferedReader(inputStreamReader);

                for (String head:headerInfoList){
                    bufferedWriter.write(head+";");
                    bufferedWriter.newLine();
                    bufferedWriter.newLine();
                }

                String lineInfo=null;
                while ((lineInfo=bufferedReader.readLine())!=null){
                    bufferedWriter.write(lineInfo);
                    bufferedWriter.newLine();
                }
                bufferedWriter.flush();
            }catch (Exception e){
                logger.error("生成基础类：{}失败",fileName,e);
            }finally {
                if(bufferedReader!=null){
                    try {
                        bufferedReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(inputStreamReader!=null){
                    try {
                        inputStreamReader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(inputStream!=null){
                    try {
                        inputStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(bufferedWriter!=null){
                    try {
                        bufferedWriter.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(outw!=null){
                    try {
                        outw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if(out!=null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
}
