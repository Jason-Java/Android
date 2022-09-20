package com.jason.config;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractDispatcherServletInitializer;

import javax.servlet.Filter;

public class ServletContainerInit extends AbstractDispatcherServletInitializer {

    //加载springMVC环境配置
    @Override
    protected WebApplicationContext createServletApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringMvcConfig.class);
        System.out.println("+=========================12345678");
        return context;
    }


    //设置拦截那些请求
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }


    //加载spring环境配置
    @Override
    protected WebApplicationContext createRootApplicationContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(SpringConfig.class);
        System.out.println("+=========================12345678");
        return context;
    }



    //设置过滤器
    @Override
    protected Filter[] getServletFilters() {
        //设置字符过滤器
        CharacterEncodingFilter characterFilter = new CharacterEncodingFilter();
        characterFilter.setEncoding("UTF-8");
        return new Filter[]{characterFilter};
    }
}
