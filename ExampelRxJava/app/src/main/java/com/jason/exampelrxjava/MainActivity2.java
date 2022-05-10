package com.jason.exampelrxjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

import util.LogUtil;

public class MainActivity2 extends AppCompatActivity {

    private ArrayList<Star> list = new ArrayList<Star>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 20; j++) {
                if (i == 0) {
                    list.add(new Star("晏传利", i));
                }
                if (i == 1) {
                    list.add(new Star("冯家振", i));
                }
                if (i == 2) {
                    list.add(new Star("汪涵", i));
                }
                if (i == 3) {
                    list.add(new Star("李俊泽", i));
                }
            }
        }

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new AdapterItemDecorItem());
        AdapterStar adapterStar = new AdapterStar(this, R.layout.recycler_view);
        adapterStar.setItem(list);
        recyclerView.setAdapter(adapterStar);


        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                LogUtil.i("我是recyclerView滑动的状态 " + recyclerView.getScrollState());



                return false;
            }
        });
    }
}