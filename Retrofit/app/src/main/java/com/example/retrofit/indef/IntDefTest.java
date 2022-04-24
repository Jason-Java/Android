package com.example.retrofit.indef;

import android.annotation.SuppressLint;
import android.widget.ImageView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;


import com.example.retrofit.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

public class IntDefTest {

    private static WeekDay mCurrentDay;
    private static int mCurrentIntDay;

    public static final int SUNDAY = 1;
    public static final int MONDAY = 1<<1;


    public static void setEmunDay(WeekDay currentDay) {
        mCurrentDay = currentDay;
    }

    public static void setIntDay(@WeekDayAnn int currentDay) {
        mCurrentIntDay = currentDay;
    }


    public void test() {
        setDrawable(R.drawable.ic_launcher_background);
        setEmunDay(WeekDay.SUNDAY);
        setIntDay(SUNDAY);
        setIntDay(1);
    }

    public static void setDrawable(@DrawableRes int id) {
//        setCurrentDay(MONDAY);
    }

}
