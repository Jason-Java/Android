package com.jason.service.impl;

import com.jason.controller.UserControl;
import com.jason.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public void save(UserControl user) {
        System.out.println("user service....");
    }
}
