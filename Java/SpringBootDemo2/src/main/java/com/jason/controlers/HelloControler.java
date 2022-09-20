package com.jason.controlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloControler {

    @Autowired
    private Person person;

    @RequestMapping("/say")
    public String say() {
        return person.toString();
    }
}
