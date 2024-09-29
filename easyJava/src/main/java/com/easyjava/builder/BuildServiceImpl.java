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

public class BuildServiceImpl {
    private static final Logger logger= LoggerFactory.getLogger(BuildServiceImpl.class);
    public static void execute(TableInfo tableInfo){
        File folder=new File(Constants.path_service_impl);
        if(!folder.exists()){
            folder.mkdirs();
        }
        String className=tableInfo.getBeanName()+"ServiceImpl";
        String interfaceName=tableInfo.getBeanName()+"Service";
        //注意这个地方不要写错类名写错的话文件结构也会发生变化
        File serviceFile=new File(folder,className+".java");

        OutputStream outputStream=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedWriter bufferedWriter=null;
        try {
            outputStream=new FileOutputStream(serviceFile);
            outputStreamWriter=new OutputStreamWriter(outputStream,"utf8");
            bufferedWriter=new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("package "+Constants.package_service_impl+";");
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
            String mapperName=tableInfo.getBeanName()+Constants.suffix_mappers;
            String mapperBeanName=StringUtils.lowerCaseFirstLetter(mapperName);
            bufferedWriter.write("import "+Constants.package_vo+".PaginationResultVO;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_query+".SimplePage;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_enums+".PageSize;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_po+"."+tableInfo.getBeanName()+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_query+"."+tableInfo.getBeanName()+"Query;");
            bufferedWriter.newLine();
            bufferedWriter.write("import javax.annotation.Resource;");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_mappers+"."+mapperName+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import "+Constants.package_service+"."+interfaceName+";");
            bufferedWriter.newLine();
            bufferedWriter.write("import org.springframework.stereotype.Service;");
            bufferedWriter.newLine();
            bufferedWriter.write("import java.util.List;");
            bufferedWriter.newLine();
            bufferedWriter.newLine();
            BuildComment.creatClassComment(bufferedWriter, tableInfo.getComment()+"ServiceImpl");
            bufferedWriter.write("@Service(\""+StringUtils.lowerCaseFirstLetter(tableInfo.getBeanName())+"Service\")");
            bufferedWriter.newLine();
            bufferedWriter.write("public class "+className+" implements "+interfaceName+" {");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            bufferedWriter.write("\t@Resource");
            bufferedWriter.newLine();

            bufferedWriter.write("\tprivate "+mapperName+"<"+tableInfo.getBeanName()+","+tableInfo.getBeanParamName()+"> "+StringUtils.lowerCaseFirstLetter(mapperName)+";");
            bufferedWriter.newLine();
            bufferedWriter.newLine();

            BuildComment.createFieldComment(bufferedWriter,"根据条件查询列表");
            bufferedWriter.write("\tpublic List<"+tableInfo.getBeanName()+"> findListByParam("+tableInfo.getBeanParamName()+" query) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn this."+mapperBeanName+".selectList(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"根据条件查询数量");
            bufferedWriter.write("\tpublic Integer findCountByParam("+tableInfo.getBeanParamName()+" query) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn this."+mapperBeanName+".selectCount(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"分页查询");
            bufferedWriter.write("\tpublic PaginationResultVO<"+tableInfo.getBeanName()+"> findListByPage("+tableInfo.getBeanParamName()+" query) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tInteger count=this.findCountByParam(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tInteger pageSize=query.getPageSize()==null?PageSize.SIZE15.getSize():query.getPageSize();");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tSimplePage page=new SimplePage(query.getPageNo(),count,pageSize);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tquery.setSimplePage(page);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tList<"+tableInfo.getBeanName()+"> list=this.findListByParam(query);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tPaginationResultVO<"+tableInfo.getBeanName()+"> result=new PaginationResultVO(count,page.getPageSize(),page.getPageNo(),page.getPageTotal(),list);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn result;");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();

            BuildComment.createFieldComment(bufferedWriter,"新增");
            bufferedWriter.write("\tpublic Integer add("+tableInfo.getBeanName()+" bean) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn this."+mapperBeanName+".insert(bean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"批量新增");
            bufferedWriter.write("\tpublic Integer addBatch(List<"+tableInfo.getBeanName()+"> listBean) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tif (listBean==null || listBean.isEmpty()) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\treturn 0;");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t}");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn this."+mapperBeanName+".insertBatch(listBean);");
            bufferedWriter.newLine();
            bufferedWriter.write("\t}");
            bufferedWriter.newLine();
            BuildComment.createFieldComment(bufferedWriter,"批量新增或修改");
            bufferedWriter.write("\tpublic Integer addOrUpdateBatch(List<"+tableInfo.getBeanName()+"> listBean) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\tif (listBean==null || listBean.isEmpty()) {");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t\treturn 0;");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\t}");
            bufferedWriter.newLine();
            bufferedWriter.write("\t\treturn this."+mapperBeanName+".insertOrUpdateBatch(listBean);");
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
                bufferedWriter.write("\tpublic "+tableInfo.getBeanName()+" get"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+") {");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn this."+mapperBeanName+".selectBy"+methodName+"("+paramBuilder+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"更新");
                bufferedWriter.write("\tpublic Integer update"+tableInfo.getBeanName()+"By"+methodName+"( "+tableInfo.getBeanName()+" bean , "+ methodParam+") {");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn this."+mapperBeanName+".updateBy"+methodName+"(bean,"+paramBuilder+");");
                bufferedWriter.newLine();
                bufferedWriter.write("\t}");
                bufferedWriter.newLine();

                bufferedWriter.newLine();
                BuildComment.createFieldComment(bufferedWriter,"根据"+methodName+"删除");
                bufferedWriter.write("\tpublic Integer delete"+tableInfo.getBeanName()+"By"+methodName+"("+methodParam+") {");
                bufferedWriter.newLine();
                bufferedWriter.write("\t\treturn this."+mapperBeanName+".deleteBy"+methodName+"("+paramBuilder+");");
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
