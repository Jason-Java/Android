package com.unite.customdrawable;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private MarqueeTextView tvText;
    private String TAG = "jason";
    private String textValue;
    private Button scrollLayoutButton;
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvText = findViewById(R.id.tvText);
        tvText.setText("我是字体滚动滚动滚动滚动");


        textValue = tvText.getText().toString();
        tvText.statMarquee();

        layout = findViewById(R.id.layout);


//      Handler handler=  new Handler(Looper.myLooper());
//      handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                tvText.setScrollX(tvText.getScrollX() + 1);
//                handler.postDelayed(this, 50);
//            }
//        },50);


//        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 100);
//        valueAnimator.setDuration(1000*5);
//        valueAnimator.setInterpolator(new LinearInterpolator());
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                int value = (int) animation.getAnimatedValue();
//                tvText.setScroller(value);
//            }
//        });
//        valueAnimator.start();


        findViewById(R.id.button).setOnClickListener(v -> {
//            tvText.scrollBy(tvText.getScrollX() + 10, tvText.getScrollY());
//
//
//            tvText.invalidate();
//
//            Log.i("TAG", "onCreate: " + tvText.getScrollX() + "  " + tvText.getScrollY());
            tvText.statMarquee();
        });
//
//
//        findViewById(R.id.start11).setOnClickListener(v -> {
//            int position=0;
//            int scrollerLength=(int)tvText.getPaint().measureText(textValue)-4;
//
//            tvText.setHorizontalFadingEdgeEnabled(true);
//            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(tvText, "ScrollX", tvText.getScrollX(), tvText.getScrollX() + scrollerLength);
//            objectAnimator.setDuration(5000);
//            objectAnimator.setInterpolator(new LinearInterpolator());
//            objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//                @Override
//                public void onAnimationUpdate(ValueAnimator animation) {
//                    tvText.measure(tvText.getWidth(), tvText.getHeight());
//                }
//            });
//            objectAnimator.addListener(new AnimatorListenerAdapter() {
//                @Override
//                public void onAnimationEnd(Animator animation) {
//                    super.onAnimationEnd(animation);
//                }
//
//            });
//
//            objectAnimator.start();
//        });
//
//
//        findViewById(R.id.scrollLayout).setOnClickListener(v -> {
//            layout.scrollBy(layout.getScrollX() + 10, layout.getScrollY() + 10);
//        });
    }


}