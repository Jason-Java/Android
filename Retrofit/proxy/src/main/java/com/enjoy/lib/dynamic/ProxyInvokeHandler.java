package com.enjoy.lib.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ProxyInvokeHandler implements InvocationHandler {

    private Object realObject;

    public ProxyInvokeHandler(Object realObject) {
        this.realObject = realObject;
    }

    /**
     *
     * @param o       代理对象M
     * @param method  调用的方法
     * @param objects 方法的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {

        return method.invoke(realObject, objects);
    }
}
