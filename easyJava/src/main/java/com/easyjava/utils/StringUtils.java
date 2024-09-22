package com.easyjava.utils;

public class StringUtils {
    public static String upperCaseFirstLetter(String s){
        if(org.apache.commons.lang3.StringUtils.isEmpty(s)){
            return s;
        }else {
            return s.substring(0,1).toUpperCase()+s.substring(1);
        }
    }
    public static String lowerCaseFirstLetter(String s){
        if(org.apache.commons.lang3.StringUtils.isEmpty(s)){
            return s;
        }else {
            return s.substring(0,1).toLowerCase()+s.substring(1);
        }
    }
}
