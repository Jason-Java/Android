package com.unite.customepack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.unite.jasonjar.domain.KeyValue;
import com.unite.jasonjar.view.ErrorView;
import com.unite.jasonjar.view.JasonAddressView;
import com.unite.jasonjar.view.JasonCalendarView;
import com.unite.jasonjar.view.MultipleSpinner;
import com.unite.jasonjar.view.StrokeDrawable;
import com.unite.jasonjar.view.WaitingView;


import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import util.LogUtil;
import util.StringUtil;

public class MainActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogUtil.i(StringUtil.timeFormat("2022-05-11T15:23:13.8474074+08:00"));
        LogUtil.i(StringUtil.timeFormat("2022-05-11 15:23:13"));
        JasonCalendarView jasonCalendarView = findViewById(R.id.jasonView);
        StrokeDrawable drawable = new StrokeDrawable();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.back_icn);
        drawable.setBitmap(bitmap);
        jasonCalendarView.setBackground(drawable);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        return super.onTouchEvent(event);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev)
    {
        return super.dispatchTouchEvent(ev);
    }
}