package com.jason.dao;

import org.springframework.stereotype.Service;

@Service
public class BookDao {

    public String save(int a,String b,User user) {
        System.out.println("save..... " + a + b + user);
        return "abcdf";
    }
}
