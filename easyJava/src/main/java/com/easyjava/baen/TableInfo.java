package com.easyjava.baen;


import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TableInfo {
    private String tableName;//表名
    private String beanName;//bean名称
    private String beanParamName;//参数名称
    private String comment;//表注释
    private List<FieldInfo> fieldList;//字段信息
    private List<FieldInfo> fieldExtendList;//扩展字段信息
    private Map<String,List<FieldInfo>> keyIndexMap=new LinkedHashMap();//唯一索引集合
    private Boolean haveDate;//是否有date类型
    private Boolean haveDateTime;//是否有时间类型
    private Boolean haveBigDecimal;//

    public String getTableName() {
        return tableName;
    }

    public String getBeanName() {
        return beanName;
    }

    public String getBeanParamName() {
        return beanParamName;
    }

    public String getComment() {
        return comment;
    }

    public List<FieldInfo> getFieldList() {
        return fieldList;
    }

    public Map<String, List<FieldInfo>> getKeyIndexMap() {
        return keyIndexMap;
    }

    public Boolean isHaveDate() {
        return haveDate;
    }

    public Boolean isHaveDateTime() {
        return haveDateTime;
    }

    public Boolean isHaveBigDecimal() {
        return haveBigDecimal;
    }



    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public void setBeanParamName(String beanParamName) {
        this.beanParamName = beanParamName;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setFieldList(List<FieldInfo> fieldList) {
        this.fieldList = fieldList;
    }

    public void setKeyIndexMap(Map<String, List<FieldInfo>> keyIndexMap) {
        this.keyIndexMap = keyIndexMap;
    }

    public void setHaveDate(Boolean haveDate) {
        this.haveDate = haveDate;
    }

    public void setHaveDateTime(Boolean haveDateTime) {
        this.haveDateTime = haveDateTime;
    }

    public void setHaveBigDecimal(Boolean haveBigDecimal) {
        this.haveBigDecimal = haveBigDecimal;
    }

    public List<FieldInfo> getFieldExtendList() {
        return fieldExtendList;
    }

    public void setFieldExtendList(List<FieldInfo> fieldExtendList) {
        this.fieldExtendList = fieldExtendList;
    }
}
