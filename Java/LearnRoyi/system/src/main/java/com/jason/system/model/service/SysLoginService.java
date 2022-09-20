package com.jason.system.model.service;

import com.jason.system.exception.ServiceException;
import com.jason.system.model.body.LoginUser;
import com.jason.system.security.AuthenticationContextHolder;
import com.jason.system.model.service.impl.SysConfigServiceImpl;
import com.jason.system.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class SysLoginService {


    @Resource
    private AuthenticationManager authenticationManager;


    @Autowired
    private SysConfigServiceImpl configService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 登陆验证
     *
     * @param username 用户名
     * @param password 用户密码
     * @param code     验证码
     * @param uuid     用户唯一标识
     * @return
     */
    public String login(String username, String password, String code, String uuid) {
        boolean captchaEnable = configService.selectCaptchaEnable();
        //验证码开关
        if (captchaEnable) {
            validateCaptcha(username, code, uuid);
        }
        //用户验证
        Authentication authentication = null;
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
            AuthenticationContextHolder.setContext(authenticationToken);
            authentication = authenticationManager.authenticate(authenticationToken);
        } catch (Exception e) {
            throw  new  ServiceException(e.getMessage());
        }
        finally {
            AuthenticationContextHolder.clearContext();
        }

        LoginUser loginUser= (LoginUser) authentication.getPrincipal();

        return tokenUtil.generateToken(loginUser.getUsername());

    }


    public void validateCaptcha(String username, String code, String uuid) {
        // TODO: 2022/9/18 redis 缓存


    }
}
