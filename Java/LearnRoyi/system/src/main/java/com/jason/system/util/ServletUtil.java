package com.jason.system.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ServletUtil {


    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }


    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    /**
     * 获取请求的路径
     * @return
     */
    public static String getURI() {
        return getRequest().getRequestURI();
    }

    /**
     * 获取请求方式 GET,POST....
     * @return
     */
    public static String getMethod() {
        return getRequest().getMethod();
    }




}
