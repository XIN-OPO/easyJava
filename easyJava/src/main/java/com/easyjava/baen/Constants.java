package com.easyjava.baen;

import com.easyjava.utils.PropertiesUtils;

public class Constants {
    public static Boolean ignore_table_prefix;
    public static String suffix_bean_param;
    public static String path_base;
    public static String package_base;
    public static String path_po;
    private static String path_java="java";
    private static String path_resources="resources";
    public static String path_utils;
    public static String path_enums;
    public static String package_po;
    public static String package_utils;
    public static String package_enums;
    public static String author_comment;
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
        suffix_bean_param=PropertiesUtils.getString("suffix.bean.param");

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
        System.out.println(path_utils);
        System.out.println(path_base);
        System.out.println(package_utils);
    }
}
