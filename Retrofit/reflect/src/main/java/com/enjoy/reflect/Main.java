package com.enjoy.reflect;

import com.enjoy.reflect.bean.Lance;

import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) throws Exception {
        Lance lance = new Lance();
        Method test1 = findMethod(lance, "test1");
        test1.invoke(lance,1,2,3);
    }

    private static Method findMethod(Object instance,
                                     String name,
                                     Class<?>... parameterTypes) throws NoSuchMethodException {
        Method method = instance.getClass().getMethod("test2", parameterTypes);
        method.setAccessible(true);
        return method;
    }
}