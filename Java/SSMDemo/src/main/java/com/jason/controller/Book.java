package com.jason.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class Book {

    @GetMapping()
    public String getAll() {
        System.out.println("msg:ok ");
        return "msg:ok ";
    }

    @PostMapping()
    public String save() {
        System.out.println("msg:ok ");
        return "msg:ok ";
    }
}
