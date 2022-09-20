package com.jason.system.exception;

import com.alibaba.fastjson.JSONObject;
import com.jason.system.model.domain.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice //开启Exception全局拦截
public class ProjectGlobalException {

    @ExceptionHandler
    public AjaxResult doException(HttpServletRequest request, Exception ex) {
        return AjaxResult.error(400, ex.getMessage());
    }
}
