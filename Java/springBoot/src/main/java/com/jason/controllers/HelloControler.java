package com.jason.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hello")
public class HelloControler {
    @RequestMapping("/world")
    public String sayHi() {
        return "hello world";
    }
}
