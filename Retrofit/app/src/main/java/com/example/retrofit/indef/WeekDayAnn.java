package com.example.retrofit.indef;

import androidx.annotation.IntDef;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@IntDef(value = {IntDefTest.SUNDAY, IntDefTest.MONDAY})
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface WeekDayAnn {  //注解

}
