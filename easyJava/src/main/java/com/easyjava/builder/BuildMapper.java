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
import java.util.List;
import java.util.Map;

public class BuildMapper {
    private static final Logger logger= LoggerFactory.getLogger(BuildMapper.class);
    public static void execute(TableInfo tableInfo){
        File folder=new File(Constants.path_mappers);
        if(!folder.exists()){
            folder.mkdirs();
        }
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        String className=tableInfo.getBeanName()+Constants.suffix_mappers;
        File poFile=new File(folder,className+".java");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(poFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.package_mappers+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import org.apache.ibatis.annotations.Param;");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            bufferedWriter.write("public interface "+className+"<T,P> extends BaseMapper {");
            bufferedWriter.newLine();

            //构建类注释
            BuildComment.creatClassComment(bufferedWriter, tableInfo.getComment());


            Map<String, List<FieldInfo>> keyIndexMap= tableInfo.getKeyIndexMap();
            for(Map.Entry<String,List<FieldInfo>> entry: keyIndexMap.entrySet()){
                List<FieldInfo> keyFieldInfoList=entry.getValue();
                Integer index=0;
                StringBuilder methodName=new StringBuilder();

                StringBuilder methodParam=new StringBuilder();

                for (FieldInfo fieldInfo:keyFieldInfoList){
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    if(index<keyFieldInfoList.size()) {
                        methodName.append("And");
                    }

                    methodParam.append("@Param(\""+fieldInfo.getPropertyName()+"\") "+fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    if(index<keyFieldInfoList.size()) {
                        methodParam.append(", ");
                    }
                }
                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
                bufferedWriter.write("\tT selectBy"+methodName+"("+methodParam+");");
                bufferedWriter.newLine();

                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.write("\tLong updateBy"+methodName+"(@Param(\"bean\") T t , "+ methodParam+");");
                bufferedWriter.newLine();

                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                bufferedWriter.write("\tLong deleteBy"+methodName+"("+methodParam+");");
                bufferedWriter.newLine();

            }

            bufferedWriter.newLine();

            bufferedWriter.write("}");
            bufferedWriter.flush();
        }catch (Exception e){
            logger.info("创建mappers失败",e);
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
