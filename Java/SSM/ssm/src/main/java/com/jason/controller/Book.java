package com.jason.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class Book {

    @GetMapping
    public String getAll() {

        return "msg:ok";
    }
}
