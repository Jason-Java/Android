package com.unite.jasonjar.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unite.jasonjar.domain.KeyValue;

import java.util.ArrayList;

public class MultipleSpinner extends androidx.appcompat.widget.AppCompatTextView {
    private Context context;
    private MultipleAdapter multipleAdapter;
    private MultipleAdapter.OnSelectItemListener onSelectItemListener;
    private MultipleChoicePop pop;


    public MultipleSpinner(@NonNull Context context) {
        super(context);
        this.context = context;
        initEvent();
    }

    public MultipleSpinner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initEvent();
    }

    public MultipleSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(MultipleAdapter adapter) {
        multipleAdapter = adapter;
    }

    private void initEvent() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopWindow();
            }
        });
    }

    public void setOnSelectItemListener(MultipleAdapter.OnSelectItemListener onSelectItemListener) {
        this.onSelectItemListener = onSelectItemListener;
    }

    private void showPopWindow() {

        if (pop == null) {
            pop = new MultipleChoicePop((Activity) context);
            pop.setAdapter(multipleAdapter);
            multipleAdapter.setOnSelectItemListener(new MultipleAdapter.OnSelectItemListener() {
                @Override
                public void select(ArrayList<KeyValue> value) {
                    String text = "";
                    for (int i = 0; i < value.size(); i++) {
                        text += value.get(i).getValue() + ";";
                    }
                    if (text.length() <= 0) {
                        return;
                    }
                    text = text.substring(0, text.length() - 1);
                    MultipleSpinner.this.setText(text);
                }
            });
        }

        pop.showPopWindow(getViewLocationInWindow()[0], getViewLocationInWindow()[1]);
    }

    private int[] getViewLocationInWindow() {
        int[] location = new int[2];
        this.getLocationInWindow(location);
        return location;
    }


    class MultipleChoicePop extends PopupWindow {
        private int height;
        private int width;
        private View view;
        private Activity activity;

        public MultipleChoicePop(Activity activity) {
            super(activity);
            this.activity = activity;
            setContentView(getView());
            setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

            //pupWindow全屏显示
            setClippingEnabled(false);
            //设置背景颜色
            ColorDrawable drawable = new ColorDrawable(0X80000000);
            setBackgroundDrawable(drawable);
            //获取焦点
            setFocusable(true);
            //设置可以点击
            setTouchable(true);
            initData();
            initEvent();
        }

        public void setAdapter(MultipleAdapter adapter) {
            ViewGroup viewParent = (ViewGroup) view;
            int size = viewParent.getChildCount();
            boolean flag = false;
            for (int i = 0; i < size; i++) {
                View childView = viewParent.getChildAt(i);
                if (childView instanceof RecyclerView) {
                    flag = true;
                    RecyclerView recycler = ((RecyclerView) childView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setAdapter(adapter);
                    break;
                }
            }
            if (!flag) {
                throw new RuntimeException("Multiple PopupWindow 布局不包含 RecyclerView,无法加载Adapter");
            }
        }

        //设置PopupWindow的宽
        public void setWidth(int width) {
            this.width = width;
        }

        //设置PopupWindow的高
        public void setHeight(int height) {
            this.height = height;
        }

        public View getView() {
            LinearLayout linearLayout = new LinearLayout(activity);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(MultipleSpinner.this.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(param);
            RecyclerView recyclerView = new RecyclerView(activity);
            recyclerView.setBackgroundColor(0XFFFFFFFF);
            recyclerView.setLayoutParams(param);
            linearLayout.addView(recyclerView);
            view = linearLayout;
            return linearLayout;
        }

        private void initData() {

        }

        private void initEvent() {

        }


        public void showPopWindow(int offsetX, int offsetY) {
//            View view = getParentView();
//            if(view)
//
//            int rootViewHeight=view.getHeight();
//            if(rootViewHeight<height+getViewLocationInWindow()[1])
//            {
//
//            }
            this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY);
        }

        //寻找Activity的根布局
        public View getParentView() {
            //寻找Activity的根布局
            ViewGroup viewGroup = activity.findViewById(android.R.id.content);
            return viewGroup.getChildAt(0);
        }

    }

}
