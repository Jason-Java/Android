package com.example.javademo.util;

public class StringUtil {

    public static String AA(String value) {
        char a=value.charAt(0);
        if(a=='"'){
            value = value.substring(1, value.length());
        }
        a = value.charAt(value.length() - 1);
        if(a=='"'){
            value = value.substring(0, value.length()-1);
        }
        return value;
    }
}
