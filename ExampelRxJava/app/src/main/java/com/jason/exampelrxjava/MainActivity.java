package com.jason.exampelrxjava;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

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

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    float oldX = 0, oldY = 0, newX = 0, newY = 0;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyFragment fragment = new MyFragment();
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.fragment, fragment, "Fragment");
        ft.commit();
         button = findViewById(R.id.btn);
        button.setOnClickListener(V -> {
            Intent intent = new Intent(this, MainActivity2.class);
            startActivity(intent);
        });

        MyRelativeLayout parent = findViewById(R.id.parent);
        parent.setOnMoverViewListener(new MyRelativeLayout.OnMoverViewListener() {
            @Override
            public void onMoverView(float oldX, float oldY, float newX, float newY) {
                button.offsetTopAndBottom((int) (newY - oldY));
            }
        });
        //button.setOnTouchListener(this);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int action = event.getAction();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                oldX = event.getX();
                oldY = event.getY();
                LogUtil.i("我是按下事件 旧X " + oldX + " 旧Y " + oldY + " 新X " + newX + " 新Y " + newY);

                break;
            case MotionEvent.ACTION_MOVE:
                newX = event.getX();
                newY= event.getY();
                button.offsetTopAndBottom((int) (newY - oldY));
                //button.layout(button.getPaddingLeft(), button.getPaddingTop(), button.getPaddingRight(), button.getBottom());
                //button.setTranslationX(newX );

//
////               ObjectAnimator animator2=ObjectAnimator.ofFloat(button,"translationX",oldX,newX);
////               animator2.setDuration(50);
//                //延y轴移动,起始位置和结束位置
//                ObjectAnimator animator3=ObjectAnimator.ofFloat(button,"translationY",oldY,newY);
//
//
//                AnimatorSet set =new  AnimatorSet();
//               // set.play(animator2);
//                set.play(animator3);
//                set.start();
//                oldX=newX;
//                oldY=newY;

                LogUtil.i("我是移动事件 旧X " + oldX + " 旧Y " + oldY + " 新X " + newX + " 新Y " + newY);

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return false;

    }
}