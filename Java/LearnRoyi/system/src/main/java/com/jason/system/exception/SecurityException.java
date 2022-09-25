package com.jason.system.exception;

import org.springframework.security.core.AuthenticationException;

public class SecurityException extends AuthenticationException {

    private int code;
    private String msg;
    /**
     * Constructs an {@code AuthenticationException} with the specified message and no
     * root cause.
     *
     * @param msg the detail message
     */
    public SecurityException(String msg) {
        super(msg);
    }

    public SecurityException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return this.msg;
    }
}
