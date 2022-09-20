package com.jason.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BookDaoImpl implements BookDao {


    @Value("${name}")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public BookDaoImpl() {
        System.out.println("book dao construct .....");
    }

    public void init() {
        System.out.println("book dao init .....");
    }

    public void destroy() {
        System.out.println("book dao destroy .....");
    }

    @Override
    public void save() {
        System.out.println("book dao save ..... "+name);
    }
}
