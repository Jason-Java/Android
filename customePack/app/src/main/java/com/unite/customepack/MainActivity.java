package com.unite.customepack;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.unite.jasonjar.domain.KeyValue;
import com.unite.jasonjar.view.ErrorView;
import com.unite.jasonjar.view.JasonAddressView;
import com.unite.jasonjar.view.MultipleSpinner;
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


public class MainActivity extends AppCompatActivity {
    SuperLoadingProgress mSuperLoadingProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyAdapter adapter = new MyAdapter(this, R.layout.my_adapter_layout);
        ArrayList<KeyValue> list = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            KeyValue keyValue = new KeyValue();
            keyValue.setKey(i + "");
            keyValue.setValue(i + " 我是value");
            list.add(keyValue);
        }
        adapter.setItem(list);
        MultipleSpinner spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
    }
}