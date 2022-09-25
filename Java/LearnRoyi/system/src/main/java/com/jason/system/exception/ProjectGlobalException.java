package com.jason.system.exception;

import com.alibaba.fastjson.JSONObject;
import com.jason.system.model.domain.AjaxResult;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice //开启Exception全局拦截
public class ProjectGlobalException {

    @ExceptionHandler(ServiceException.class)
    public AjaxResult doException(HttpServletRequest request, Exception ex) {
        return AjaxResult.error(400, ex.getMessage());
    }

    /**
     * 获取校验异常
     * @param request
     * @param ex
     * @return
     */
    @ExceptionHandler(BindException.class)
    public AjaxResult doException1(HttpServletRequest request, BindException ex) {
        return AjaxResult.error(400, ex.getFieldError().getDefaultMessage());
    }

}
