package com.example.retrofit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.CLASS) //保留时
@Target({ElementType.TYPE})  //作用目标
public @interface Lance {
    int a();
    String b();
}
