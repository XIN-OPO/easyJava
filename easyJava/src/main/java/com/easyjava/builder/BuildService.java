package com.easyjava.builder;

import com.easyjava.baen.Constants;
import com.easyjava.baen.FieldInfo;
import com.easyjava.baen.TableInfo;
import com.easyjava.utils.StringUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;
import java.util.Map;

public class BuildService {
    private static final Logger logger= LoggerFactory.getLogger(BuildService.class);
    public static void execute(TableInfo tableInfo){
        File folder=new File(Constants.path_service);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String className=tableInfo.getBeanName()+"Service";
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        File serviceFile=new File(folder,className+".java");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(serviceFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.package_service+";");
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
            bufferedWriter.write("import "+Constants.package_vo+".PaginationResultVO;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_po+"."+tableInfo.getBeanName()+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_query+"."+tableInfo.getBeanName()+"Query;");
            bufferedWriter.newLine();
            bufferedWriter.write("import java.util.List;");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.creatClassComment(bufferedWriter, tableInfo.getComment()+"Service");
            bufferedWriter.write("public interface "+className+"{");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询列表");
            bufferedWriter.write("\tList<"+tableInfo.getBeanName()+"> findListByParam("+tableInfo.getBeanParamName()+" query);");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询数量");
            bufferedWriter.write("\tInteger findCountByParam("+tableInfo.getBeanParamName()+" query);");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"分页查询");
            bufferedWriter.write("\tPaginationResultVO<"+tableInfo.getBeanName()+"> findListByPage("+tableInfo.getBeanParamName()+" query);");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"新增");
            bufferedWriter.write("\tInteger add("+tableInfo.getBeanName()+" bean);");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"批量新增");
            bufferedWriter.write("\tInteger addBatch(List<"+tableInfo.getBeanName()+"> listBean);");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"批量新增或修改");
            bufferedWriter.write("\tInteger addOrUpdateBatch(List<"+tableInfo.getBeanName()+"> listBean);");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

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

                    methodParam.append(fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    if(index<keyFieldInfoList.size()) {
                        methodParam.append(", ");
                    }
                }
                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
                bufferedWriter.write("\t"+tableInfo.getBeanName()+" get"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+");");
                bufferedWriter.newLine();

                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.write("\tInteger update"+tableInfo.getBeanName()+"By"+methodName+"( "+tableInfo.getBeanName()+" bean , "+ methodParam+");");
                bufferedWriter.newLine();

                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                bufferedWriter.write("\tInteger delete"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+");");
                bufferedWriter.newLine();

            }

            bufferedWriter.write("}");


            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception e){
            logger.info("创建service失败",e);
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
