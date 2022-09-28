package com.jason.system.util;


import com.jason.system.model.body.LoginUser;
import com.jason.system.model.domain.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

public class SecurityUtil {


    /**
     * 检测原密码是否与加密的密码相同
     *
     * @param rawPassword    原密码
     * @param encodePassword 加密后的密码
     * @return true相等, false不相等
     */
    public static final boolean passwordMatch(String rawPassword, String encodePassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodePassword);
    }

    /**
     * 获取用户登录信息
     * @return
     */
    public static LoginUser getLoginUser() {

        return (LoginUser) getAuthentication().getPrincipal();
    }

    public static List<SysRole> getRoles() {
      return  getLoginUser().getUser().getRoles();
    }

    /**
     * 获取当前用户的权限
     * @return 权限集合
     */
    public static List<String> getPermission() {
        return getLoginUser().getPermissions();
    }

    private static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }





}
