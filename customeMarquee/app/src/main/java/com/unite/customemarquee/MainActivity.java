package com.unite.customemarquee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

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

    private MarqueeTextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);


        findViewById(R.id.but).setOnClickListener(v -> {
//            //tv.statMarquee();
//            ComponentName componentName = new ComponentName("com.unite.customdrawable", "com.unite.customdrawable.MainActivity2");
//            Intent intent = new Intent();
//            intent.setComponent(componentName);
//            startActivity(intent);


        });
        findViewById(R.id.but2).setOnClickListener(v -> {

        });
       Observable.create(new ObservableOnSubscribe<Integer>() {
           @Override
           public void subscribe(ObservableEmitter<Integer> e) throws Exception {
               e.onNext(1);
               //e.onError(null);
           }
       }).retryWhen(new Function<Observable<Throwable>, ObservableSource<?>>() {
           @Override
           public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
               return throwableObservable;
           }
       }).subscribe(new Consumer<Integer>() {
           @Override
           public void accept(Integer integer) throws Exception {
               LogUtil.i("我是接受数据 " + integer);
           }
       });
    }
}