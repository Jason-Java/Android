package com.unite.customemarquee;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import util.LogUtil;

public class MainActivity extends AppCompatActivity {

    private ArrayList<HashMap<String, ArrayList<String>>> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        HashMap<String, ArrayList<String>> map1 = new HashMap<>();
        ArrayList<String> mapArray1 = new ArrayList<>();
        mapArray1.add("1");
        mapArray1.add("2");
        mapArray1.add("3");
        map1.put("组", mapArray1);
        HashMap<String, ArrayList<String>> map2 = new HashMap<>();
        ArrayList<String> mapArray2 = new ArrayList<>();
        mapArray2.add("a");
        mapArray2.add("b");
        mapArray2.add("c");
        map2.put("组", mapArray2);
        arrayList.add(map1);
        arrayList.add(map2);


        RecyclerView parent = findViewById(R.id.ParentRecyler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,1);
        parent.setLayoutManager(gridLayoutManager);
        ParentAdapter parentAdapter = new ParentAdapter(this,R.layout.adapter_layout);
        parentAdapter.setItem(arrayList);
        parent.setAdapter(parentAdapter);

    }
}