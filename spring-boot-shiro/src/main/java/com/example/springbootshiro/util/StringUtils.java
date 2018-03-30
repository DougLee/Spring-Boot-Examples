package com.example.springbootshiro.util;

import jdk.nashorn.internal.ir.ReturnNode;

/**
 * Created by Douglee on 2018/3/30.
 */
public class StringUtils {

    public static String getStringValue(Object object){
        if (object == null) {
            return "";
        }
        return object.toString();
    }

    public static int getStringLength(String str){
        if (str == null) {
            return 0;
        }
        return str.getBytes().length;
    }

    public static boolean isNullorEmpty(Object object){
        if (object == null || object.toString().trim().equals("") ||
                object.toString().trim().equalsIgnoreCase("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNumber(String str) {
        if (str == null || "".equals(str)) {
            return false;
        }
        String reg = "^[-|+]?\\d*([.]\\d+)?$";
        return str.matches(reg);
    }

    public static int toInteger(String str) {
        return Integer.parseInt(str);
    }


}
