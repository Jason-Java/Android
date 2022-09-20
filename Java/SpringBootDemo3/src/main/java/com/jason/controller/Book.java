package com.jason.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/books")
public class Book {


    @Autowired
    private Enterprise enterprise;

    @GetMapping("/{id}")
    public String getBook(@PathVariable Integer id) {
        System.out.println("book id " + id);

        return "query ok " +enterprise.toString();
    }
}
