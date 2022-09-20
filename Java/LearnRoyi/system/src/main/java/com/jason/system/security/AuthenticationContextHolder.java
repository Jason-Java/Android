package com.jason.system.security;

import org.springframework.security.core.Authentication;

/**
 * <p>描述: 身份认证上下文
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/9/18 20:51
 * @see
 */
public class AuthenticationContextHolder {

    private static final ThreadLocal<Authentication> contextHolder = new ThreadLocal<>();

    public static Authentication getContext() {
        return contextHolder.get();
    }

    public static  void setContext(Authentication context)
    {
        contextHolder.set(context);
    }

    public static void clearContext() {
        contextHolder.remove();
    }

}
