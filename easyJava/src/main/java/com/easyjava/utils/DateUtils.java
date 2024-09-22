package com.easyjava.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;




public class DateUtils {
    public static  final String YYYY_MM_DD="yyyy-MM-dd";
    public static  final String YYYY_MM_DD_HH_mm_SS="yyyy-MM-dd HH:mm:ss";
    public static  final String _YYYYMMDD="yyyy/MM/dd";
    public static  final String YYYYMMDD="yyyyMMdd";


    public static String format(Date date,String pattern){
        return new SimpleDateFormat(pattern).format(date);
    }
    public static Date parse(Date date,String pattern){
        try {
            new SimpleDateFormat(pattern).parse(String.valueOf(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}


