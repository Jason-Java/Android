package com.jason.exampelrxjava;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class findByIdViewUtil {
    public static void findByIdViewAll(Activity activity) {
        Class cls = activity.getClass();

        //获取此类声明的所有方法
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            //判断属性是否被注解声明
            if (field.isAnnotationPresent(FindByViewAnnotation.class)) {
                FindByViewAnnotation findByViewAnnotation = field.getAnnotation(FindByViewAnnotation.class);
                //获取注解的值
                int id = findByViewAnnotation.value();
                View view = activity.findViewById(id);
                field.setAccessible(true);
                try {
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
