package com.jason.config;

import com.jason.controller.ProjectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringBootConfig implements WebMvcConfigurer {

    @Autowired
    private ProjectInterceptor projectInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(projectInterceptor).addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                //允许跨域的路径
                .addMapping("/**")
                //允许跨域的请求域名
                .allowedOriginPatterns("*")
                //是否允许cookie
                .allowCredentials(true)
                //设置允许的请求
                .allowedHeaders("GET", "POST", "DELETE", "PUT")
                //设置允许的handler属性
                .allowedHeaders("*")
                .maxAge(3600);
    }
}
