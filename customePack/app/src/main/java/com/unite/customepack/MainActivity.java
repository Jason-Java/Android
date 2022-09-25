package com.unite.customepack;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.unite.jasonjar.util.LogUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.as).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        System.out.println("123");
    }
    
}