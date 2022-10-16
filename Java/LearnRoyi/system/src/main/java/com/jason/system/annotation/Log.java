package com.jason.system.annotation;

import com.jason.system.constant.LogAction;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface  Log {

    /**
     * 模块
     */
    public String title() default "";

    /**
     * 行为
     */
    public String action() default LogAction.OTHER;


    /**
     * 是否保存请求的参数
     */
    public boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    public boolean isSaveResponseData() default true;

}
