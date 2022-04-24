package com.enjoy.reflect;

import java.lang.reflect.Field;

public class ReflectTest {
    //编译期间final类型的数据自动被优化
    public final int i = 1 ;

    public ReflectTest() {
//            i = 1;
    }

    public int getI() {
        return i;
    }

    public static void main(String[] args) throws Exception {
        ReflectTest reflectTest = new ReflectTest();
        Field i = ReflectTest.class.getDeclaredField("i");
        i.setAccessible(true);
        i.set(reflectTest, 2);

    // 动态拿
        System.out.println(i.get(reflectTest));
        // 编译时写死
        System.out.println(reflectTest.i);
    }
}
