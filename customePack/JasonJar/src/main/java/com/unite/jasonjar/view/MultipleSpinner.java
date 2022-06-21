package com.unite.jasonjar.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.service.controls.Control;
import android.util.AttributeSet;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.unite.jasonjar.domain.KeyValue;

import java.util.ArrayList;

import util.DensityUtil;

/**
 * 多选框下拉列表
 */
public class MultipleSpinner extends androidx.appcompat.widget.AppCompatTextView {
    private Context context;
    private MultipleAdapter multipleAdapter;
    private MultipleChoicePop pop;
    private ArrayList<KeyValue> selectItem;
    private MultipleAdapter.OnSelectItemListener onSelectItemListener;
    private RecyclerView recyclerView;


    public void setOnSelectItemListener(MultipleAdapter.OnSelectItemListener onSelectItemListener) {
        this.onSelectItemListener = onSelectItemListener;
    }

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


    private void showPopWindow() {
        if (pop == null) {
            pop = new MultipleChoicePop((Activity) context);
            pop.setAdapter(multipleAdapter);
            multipleAdapter.setOnSelectItemListener(new MultipleAdapter.OnSelectItemListener() {
                @Override
                public void select(ArrayList<KeyValue> value) {
                    if (onSelectItemListener != null) onSelectItemListener.select(value);

                    selectItem = new ArrayList<>();
                    selectItem.addAll(value);
                    String text = "";
                    for (int i = 0; i < value.size(); i++) {
                        text += value.get(i).getValue() + ";";
                    }
                    if (text.length() <= 0) {
                        MultipleSpinner.this.setText("");
                        return;
                    }
                    text = text.substring(0, text.length() - 1);
                    MultipleSpinner.this.setText(text);
                }
            });
        }
        pop.showPopWindow(getViewLocationInWindow()[0], getViewLocationInWindow()[1]);
    }

    /**
     * 获取选中的列表
     */
    public ArrayList<KeyValue> getSelectItem() {
        return selectItem;
    }

    private int[] getViewLocationInWindow() {
        int[] location = new int[2];
        this.getLocationInWindow(location);
        return location;
    }


    class MultipleChoicePop extends PopupWindow {
        private Activity activity;

        public MultipleChoicePop(Activity activity) {
            super(activity);
            this.activity = activity;
            setContentView(getView());
            //pupWindow全屏显示
            setClippingEnabled(false);
            //设置背景颜色
            ColorDrawable drawable = new ColorDrawable(0X80000000);
            setBackgroundDrawable(drawable);
            //获取焦点
            setFocusable(true);
            //设置可以点击
            setTouchable(true);
        }

        public void setAdapter(MultipleAdapter adapter) {
            LinearLayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter);
        }

        public View getView() {
            LinearLayout linearLayout = new LinearLayout(activity);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(MultipleSpinner.this.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(param);
            linearLayout.setElevation(DensityUtil.dp2px( 10));
            recyclerView = new RecyclerView(activity);
            recyclerView.setBackgroundColor(0XFFEBEBEB);
            recyclerView.setLayoutParams(param);
            linearLayout.addView(recyclerView);
            return linearLayout;
        }

        public void showPopWindow(int offsetX, int offsetY) {
            int multipleSpinnerHeight = MultipleSpinner.this.getHeight();
            int windowHeight = getParentView().getHeight();
            recyclerView.measure(0, 0);
            int recyclerHeight = recyclerView.getMeasuredHeight();
            if (recyclerHeight > windowHeight * 0.4) {
                setHeight((int) (windowHeight * 0.4));
            } else {
                setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
            }
            this.getContentView().measure(this.getContentView().getMeasuredWidth(),this.getContentView().getHeight());
            int popHeight =  this.getContentView().getMeasuredHeight();
            if ((popHeight + offsetY + multipleSpinnerHeight) < windowHeight) {
                this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY + multipleSpinnerHeight);
            } else {
                this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY - popHeight);
            }
        }

        //寻找Activity的根布局
        public View getParentView() {
            //寻找Activity的根布局
            ViewGroup viewGroup = activity.findViewById(android.R.id.content);
            return viewGroup.getChildAt(0);
        }
    }
}
