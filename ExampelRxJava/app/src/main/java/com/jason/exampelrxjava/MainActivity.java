package com.jason.exampelrxjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import java.util.Random;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.functions.Functions;
import io.reactivex.schedulers.Schedulers;
import util.LogUtil;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /**
         * just     操作符   创建Observable 只发送一个创建Observable
         * form     操作符   创建Observable 可以把其他对象转变成Observable对象进行发射 例如数组 new String[]{"123","456"}-->转换成 两个Observable对象发射
         * create           创建一个Observable对象
         * map              转换对象 例如 String -->转换成Integer
         * flatMap          将其他类型转换成一个多个Observable进行请求 但是不能保证请求的顺序
         * concatMap        将其他类型转换成一个多个Observable进行请求 但能保证请求的顺序
         *
         * concat           接受多个Observable对象然后有序的发射数据
         * filter           满足条件是发送到subscribe否则不发送
         */
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(@NonNull Integer integer) throws Exception {
                        if(integer % 2 == 0)
                        {
                            return true;
                        }
                        return integer % 2 == 0;
                    }
                }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                LogUtil.i("我是开始");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                LogUtil.i("我是接受" + integer);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                LogUtil.i("我是错误" + e.getMessage());

            }

            @Override
            public void onComplete() {
                LogUtil.i("我是结束");

            }
        });
    }


}