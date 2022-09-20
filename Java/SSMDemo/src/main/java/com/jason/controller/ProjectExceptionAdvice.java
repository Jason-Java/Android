package com.jason.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice //开启器异常处理器
public class ProjectExceptionAdvice {


    @ExceptionHandler
    public String doException(Exception ex) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 400);
        jsonObject.put("msg","你出错了好吗");
        return jsonObject.toJSONString();
    }

}
