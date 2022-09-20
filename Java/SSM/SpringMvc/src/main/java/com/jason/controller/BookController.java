package com.jason.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("book")
public class BookController {

    public BookController() {
        System.out.println("book init");
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save() {
        return "{'save','name'}";
    }
}
