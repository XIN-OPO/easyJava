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

public class BuildController {
    private static final Logger logger= LoggerFactory.getLogger(BuildController.class);
    public static void execute(TableInfo tableInfo){
        File folder=new File(Constants.path_controller);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String className=tableInfo.getBeanName()+"Controller";
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        File serviceFile=new File(folder,className+".java");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(serviceFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.package_controller+";");
            bufferedWriter.newLine();
//            if(tableInfo.isHaveDateTime()|| tableInfo.isHaveDate()){
//                bufferedWriter.write("import java.util.Date;");
//                bufferedWriter.newLine();
//                bufferedWriter.write(Constants.bean_date_format_class+";");
//                bufferedWriter.newLine();
//                bufferedWriter.write(Constants.bean_date_unformat_class+";");
//                bufferedWriter.newLine();
//                bufferedWriter.write("import "+Constants.package_enums+".DateTimePatternEnum;");
//                bufferedWriter.newLine();
//                bufferedWriter.write("import "+Constants.package_utils+".DateUtils;");
//                bufferedWriter.newLine();
//                bufferedWriter.newLine();
//            }
            String serviceName=tableInfo.getBeanName()+"Service";
            String serviceBeanName=StringUtils.lowerCaseFirstLetter(serviceName);
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_po+"."+tableInfo.getBeanName()+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_query+"."+tableInfo.getBeanName()+"Query;");
            bufferedWriter.newLine();
            bufferedWriter.write("import javax.annotation.Resource;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_service+"."+serviceName+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import org.springframework.web.bind.annotation.RestController;");
            bufferedWriter.newLine();
            bufferedWriter.write("import org.springframework.web.bind.annotation.RequestMapping;");
            bufferedWriter.newLine();
            bufferedWriter.write("import org.springframework.web.bind.annotation.RequestBody;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_vo+".ResponseVO;");
            bufferedWriter.newLine();
            bufferedWriter.write("import java.util.List;");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.creatClassComment(bufferedWriter, tableInfo.getComment()+"Controller");
            bufferedWriter.write("@RestController");
            bufferedWriter.newLine();
            bufferedWriter.write("@RequestMapping(\""+StringUtils.lowerCaseFirstLetter(tableInfo.getBeanName())+"\")");
            bufferedWriter.newLine();
            bufferedWriter.write("public class "+className+" extends ABaseController {");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("\t@Resource");
            bufferedWriter.newLine();

            bufferedWriter.write("\tprivate "+serviceName+" "+serviceBeanName+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("\t@RequestMapping(\"loadDataList\")");
            bufferedWriter.newLine();
            bufferedWriter.write("\tpublic ResponseVO loadDataList("+tableInfo.getBeanParamName()+" query) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn getSuccessResponseVO("+serviceBeanName+".findListByPage(query));");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();

            BuildComment.createFieldComment(bufferedWriter,"新增");
            bufferedWriter.newLine();
            bufferedWriter.write("\t@RequestMapping(\"add\")");
            bufferedWriter.newLine();
            bufferedWriter.write("\tpublic ResponseVO add("+tableInfo.getBeanName()+" bean) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tthis."+serviceBeanName+".add(bean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn getSuccessResponseVO(null);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"批量新增");
            bufferedWriter.newLine();
            bufferedWriter.write("\t@RequestMapping(\"addBatch\")");
            bufferedWriter.newLine();
            bufferedWriter.write("\tpublic ResponseVO addBatch(@RequestBody List<"+tableInfo.getBeanName()+"> listBean) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tthis."+serviceBeanName+".addBatch(listBean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn getSuccessResponseVO(null);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"批量新增或修改");
            bufferedWriter.newLine();
            bufferedWriter.write("\t@RequestMapping(\"addOrUpdateBatch\")");
            bufferedWriter.newLine();
            bufferedWriter.write("\tpublic ResponseVO addOrUpdateBatch(@RequestBody List<"+tableInfo.getBeanName()+"> listBean) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tthis."+serviceBeanName+".addOrUpdateBatch(listBean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn getSuccessResponseVO(null);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();

            Map<String, List<FieldInfo>> keyIndexMap= tableInfo.getKeyIndexMap();
            for(Map.Entry<String,List<FieldInfo>> entry: keyIndexMap.entrySet()){
                List<FieldInfo> keyFieldInfoList=entry.getValue();
                Integer index=0;
                StringBuilder methodName=new StringBuilder();

                StringBuilder methodParam=new StringBuilder();

                StringBuilder paramBuilder=new StringBuilder();

                for (FieldInfo fieldInfo:keyFieldInfoList){
                    index++;
                    methodName.append(StringUtils.upperCaseFirstLetter(fieldInfo.getPropertyName()));
                    if(index<keyFieldInfoList.size()) {
                        methodName.append("And");
                    }

                    methodParam.append(fieldInfo.getJavaType()+" "+fieldInfo.getPropertyName());
                    paramBuilder.append(fieldInfo.getPropertyName());
                    if(index<keyFieldInfoList.size()) {
                        methodParam.append(", ");
                        paramBuilder.append(", ");
                    }
                }
                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"查询");
                bufferedWriter.newLine();
                String methdName="get"+tableInfo.getBeanName()+"By"+methodName;
                bufferedWriter.write("\t@RequestMapping(\""+methdName+"\")");
                bufferedWriter.newLine();
                bufferedWriter.write("\tpublic ResponseVO get"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+") {");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn getSuccessResponseVO(this."+serviceBeanName+".get"+tableInfo.getBeanName()+"By"+methodName+"("+paramBuilder+"));");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.newLine();
                methdName="update"+tableInfo.getBeanName()+"By"+methodName;
                bufferedWriter.write("\t@RequestMapping(\""+methdName+"\")");
                bufferedWriter.newLine();
                bufferedWriter.write("\tpublic ResponseVO update"+tableInfo.getBeanName()+"By"+methodName+"( "+tableInfo.getBeanName()+" bean , "+ methodParam+") {");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\tthis."+serviceBeanName+".update"+tableInfo.getBeanName()+"By"+methodName+"(bean,"+paramBuilder+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn getSuccessResponseVO(null);");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();

                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                methdName="delete"+tableInfo.getBeanName()+"By"+methodName;
                bufferedWriter.write("\t@RequestMapping(\""+methdName+"\")");
                bufferedWriter.newLine();
                bufferedWriter.write("\tpublic ResponseVO "+methdName+"("+methodParam+") {");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\tthis."+serviceBeanName+".delete"+tableInfo.getBeanName()+"By"+methodName+"("+paramBuilder+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn getSuccessResponseVO(null);");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();
            }

            bufferedWriter.write("}");


            bufferedWriter.newLine();
            bufferedWriter.flush();
        }catch (Exception e){
            logger.info("创建service impl失败",e);
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
