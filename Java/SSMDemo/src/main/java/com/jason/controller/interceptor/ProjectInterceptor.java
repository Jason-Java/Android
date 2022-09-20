package com.jason.controller.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 添加拦截器 对方问功能进行增强
 */
@Component
public class ProjectInterceptor implements HandlerInterceptor {

    public ProjectInterceptor() {
        System.out.println("init..........");
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String head = request.getHeader("Content-Type");

        System.out.println("preHandle--->>>>>>" + head);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle--->>>>>>");
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        System.out.println("afterCompletion--->>>>>>" + response.getWriter());
    }
}
