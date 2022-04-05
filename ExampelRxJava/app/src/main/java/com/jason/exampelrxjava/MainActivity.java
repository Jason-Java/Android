package com.jason.exampelrxjava;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.ApiFactory;
import net.INetworkResponse;
import net.domain.DomainGetInfoByToken;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;
import java.util.concurrent.locks.Lock;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
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
         * flatMap          将其他类型转换成多个Observable进行请求 但是不能保证请求的顺序
         * concatMap        将其他类型转换成多个Observable进行请求 但能保证请求的顺序
         *
         * concat           接受多个Observable对象然后有序的发射数据
         * filter           满足条件是发送到subscribe否则不发送
         */


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("A");
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<String, String>() {
                    @Override
                    public String apply(@NonNull String s) throws Exception {
                        return null;
                    }
                })
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull String s) {

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


        ApiFactory.getLoginJWTTTokenInstance().loginAndUserInfo("zhongj", "123456", new INetworkResponse<DomainGetInfoByToken>() {
            @Override
            public void success(DomainGetInfoByToken value) {
                LogUtil.i("我是登录信息 " + value.getUser().getUserName());
            }

            @Override
            public boolean error(int e, String msg) {
                return false;
            }
        });

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //                ApiFactory.getReagentBrandInstance().getAll(new INetworkResponse<List<String>>() {
//                    @Override
//                    public void success(List<String> value) {
//
//                    }
//
//                    @Override
//                    public boolean error(int e, String msg) {
//                        return false;
//                    }
//                });
            }
        });
    }


}