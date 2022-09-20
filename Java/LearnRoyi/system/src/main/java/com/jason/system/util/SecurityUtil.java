package com.jason.system.util;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityUtil {


    /**
     * 检测原密码是否与加密的密码相同
     *
     * @param rawPassword 原密码
     * @param encodePassword 加密后的密码
     * @return true相等,false不相等
     */
    public static final boolean passwordMatch(String rawPassword, String encodePassword) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder.matches(rawPassword, encodePassword);
    }
}
