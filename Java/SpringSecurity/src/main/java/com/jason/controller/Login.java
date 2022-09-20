package com.jason.controller;

import com.jason.domain.Result;
import com.jason.service.LoginService;
import com.jason.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/logins")
public class Login {

    @Autowired
    private LoginService loginService;

    @GetMapping
    public Result<String> login(String name, String password) {

        return loginService.login(name,password);
    }
}
