package com.easyjava.baen;

import com.easyjava.utils.PropertiesUtils;

public class Constants {
    public static Boolean ignore_table_prefix;
    public static String suffix_bean_query;
    public static String suffix_bean_query_fuzzy;
    public static String suffix_bean_query_time_start;
    public static String suffix_bean_query_time_end;
    public static String suffix_mappers;

    public static String path_base;
    public static String package_base;
    public static String path_po;
    private static String path_java="java";
    private static String path_resources="resources";
    public static String path_utils;
    public static String path_enums;
    public static String path_query;
    public static String path_mappers;
    public static String path_mapper_xmls;
    public static String path_service;
    public static String path_service_impl;
    public static String path_vo;

    public static String package_po;
    public static String package_utils;
    public static String package_enums;
    public static String package_query;
    public static String package_mappers;
    public static String author_comment;
    public static String package_service;
    public static String package_service_impl;
    public static String package_vo;
    //需要忽略的属性
    public static String ignore_bean_tojson_filed;
    public static String ignore_bean_tojson_expression;
    public static String ignore_bean_tojson_class;
    //日期的序列化和反序列化
    public static String bean_date_format_expression;
    public static String bean_date_format_class;
    public static String bean_date_unformat_expression;
    public static String bean_date_unformat_class;

    static {
        ignore_table_prefix= Boolean.valueOf(PropertiesUtils.getString("ignore.table.prefix"));
        suffix_bean_query=PropertiesUtils.getString("suffix.bean.query");
        suffix_bean_query_fuzzy=PropertiesUtils.getString("suffix.bean.query.fuzzy");
        suffix_bean_query_time_start=PropertiesUtils.getString("suffix.bean.query.time.start");
        suffix_bean_query_time_end=PropertiesUtils.getString("suffix.bean.query.time.end");
        suffix_mappers=PropertiesUtils.getString("suffix.mappers");

        path_base=PropertiesUtils.getString("path.base");
        path_base=path_base+path_java+"/"+PropertiesUtils.getString("package.base");
        path_base=path_base.replace(".","/");

        path_po=path_base+"/"+PropertiesUtils.getString("package.po").replace(".","/");

        package_base=PropertiesUtils.getString("package.base");
        package_po=package_base+"."+PropertiesUtils.getString("package.po");
        package_utils=package_base+"."+PropertiesUtils.getString("package.utils");
        path_utils=path_base+"/"+PropertiesUtils.getString("package.utils").replace(".","/");
        package_enums=package_base+"."+PropertiesUtils.getString("package.enums");
        path_enums=path_base+"/"+PropertiesUtils.getString("package.enums").replace(".","/");
        package_query=package_base+"."+PropertiesUtils.getString("package.query");
        path_query=path_base+"/"+PropertiesUtils.getString("package.query").replace(".","/");
        package_mappers=package_base+"."+PropertiesUtils.getString("package.mappers");
        path_mappers=path_base+"/"+PropertiesUtils.getString("package.mappers").replace(".","/");
        path_mapper_xmls=PropertiesUtils.getString("path.base")+path_resources+"/"+package_mappers.replace(".","/");
        package_service=package_base+"."+PropertiesUtils.getString("package.service");
        package_service_impl=package_base+"."+PropertiesUtils.getString("package_service_impl");
        path_service=path_base+"/"+PropertiesUtils.getString("package.service").replace(".","/");
        path_service_impl=path_base+"/"+PropertiesUtils.getString("package.service.impl").replace(".","/");
        package_vo=package_base+"."+PropertiesUtils.getString("package.vo");
        path_vo=path_base+"/"+PropertiesUtils.getString("package.vo").replace(".","/");

        author_comment=PropertiesUtils.getString("author.comment");

        ignore_bean_tojson_filed=PropertiesUtils.getString("ignore.bean.tojson.filed");
        ignore_bean_tojson_expression=PropertiesUtils.getString("ignore.bean.tojson.expression");
        ignore_bean_tojson_class=PropertiesUtils.getString("ignore.bean.tojson.class");
        bean_date_format_expression=PropertiesUtils.getString("bean.date.format.expression");
        bean_date_format_class=PropertiesUtils.getString("bean.date.format.class");
        bean_date_unformat_expression=PropertiesUtils.getString("bean.date.unformat.expression");
        bean_date_unformat_class=PropertiesUtils.getString("bean.date.unformat.class");
    }
    public final static String[] SQL_DATE_TIME_TYPES=new String[]{"datetime","timestamp"};
    public final static String[] SQL_DATE_TYPES=new String[]{"date"};
    public final static String[] SQL_DECIMAL_TYPES=new String[]{"decimal","double","float"};
    public final static String[] SQL_STRING_TYPES=new String[]{"char","varchar","text","mediumtext","longtext"};
    public final static String[] SQL_INTEGER_TYPES=new String[]{"int","tinyint"};
    public final static String[] SQL_LONG_TYPES=new String[]{"bigint"};

    public static void main(String[] args) {
        System.out.println(path_mapper_xmls);
    }
}
