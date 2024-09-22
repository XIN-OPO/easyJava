package com.easyjava.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class PropertiesUtils {
    private static Properties properties=new Properties();
    private static Map<String,String> properMap=new ConcurrentHashMap();
    static {
        InputStream inputStream=null;
        try {
            inputStream=PropertiesUtils.class.getClassLoader().getResourceAsStream("application.properties");
            //读取属性列表
            properties.load(inputStream);
            //放入到Iterator中
            Iterator<Object> iterator=properties.keySet().iterator();
            while (iterator.hasNext()){
                String key=(String)iterator.next();
                properMap.put(key, properties.getProperty(key));
            }
        }catch (Exception e){

        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getString(String key){
        return properMap.get(key);
    }

    public static void main(String[] args) {
        System.out.println(getString("db.driver.name"));
    }
}
