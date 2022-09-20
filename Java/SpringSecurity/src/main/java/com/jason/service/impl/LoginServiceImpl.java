package com.jason.service.impl;

import com.jason.domain.Result;
import com.jason.service.LoginService;
import com.jason.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result login(String name, String password) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(name, password);
        Authentication authentication= authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authentication)) {
             return  new Result<>(400, "认证失败");
        }





        return  new Result<>(200, "认证成功",jwtUtil.generateToken(name));
    }
}
