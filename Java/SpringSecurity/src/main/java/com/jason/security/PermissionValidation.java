package com.jason.security;



import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 自定义权限认证方法
 */
@Component("PermissionValidation")
public class PermissionValidation {

    public boolean hasAuthority(String author) {
        Collection<? extends GrantedAuthority> authorityList = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (GrantedAuthority g :
                authorityList) {
            if (g.getAuthority().equals(author))
            {
                return true;
            }
        }
        return false;

    }
}
