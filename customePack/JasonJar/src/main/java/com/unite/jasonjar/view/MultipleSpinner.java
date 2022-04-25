package com.unite.jasonjar.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.service.controls.Control;
import android.util.AttributeSet;
import android.view.Gravity;
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

import util.DensityUtil;

public class MultipleSpinner extends androidx.appcompat.widget.AppCompatTextView
{
    private Context context;
    private MultipleAdapter multipleAdapter;
    private MultipleChoicePop pop;
    private ArrayList<KeyValue> selectItem;


    public MultipleSpinner(@NonNull Context context)
    {
        super(context);
        this.context = context;
        initEvent();
    }

    public MultipleSpinner(@NonNull Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        this.context = context;
        initEvent();
    }

    public MultipleSpinner(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(MultipleAdapter adapter)
    {
        multipleAdapter = adapter;
    }

    private void initEvent()
    {
        this.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showPopWindow();
            }
        });
    }


    private void showPopWindow()
    {
        if (pop == null)
        {
            pop = new MultipleChoicePop((Activity) context);
            pop.setAdapter(multipleAdapter);
            multipleAdapter.setOnSelectItemListener(new MultipleAdapter.OnSelectItemListener()
            {
                @Override
                public void select(ArrayList<KeyValue> value)
                {
                    if (selectItem == null)
                    {
                        selectItem = new ArrayList<>();
                    }
                    selectItem.clear();
                    selectItem.addAll(value);
                    String text = "";
                    for (int i = 0; i < value.size(); i++)
                    {
                        text += value.get(i).getValue() + ";";
                    }
                    if (text.length() <= 0)
                    {
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
    public ArrayList<KeyValue> getSelectItem()
    {
        return selectItem;
    }

    private int[] getViewLocationInWindow()
    {
        int[] location = new int[2];
        this.getLocationInWindow(location);
        return location;
    }


    class MultipleChoicePop extends PopupWindow
    {
        private int height;
        private int width;
        private View view;
        private Activity activity;

        public MultipleChoicePop(Activity activity)
        {
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
            initData();
            initEvent();
        }

        public void setAdapter(MultipleAdapter adapter)
        {
            ViewGroup viewParent = (ViewGroup) view;
            int size = viewParent.getChildCount();
            boolean flag = false;
            for (int i = 0; i < size; i++)
            {
                View childView = viewParent.getChildAt(i);
                if (childView instanceof RecyclerView)
                {
                    flag = true;
                    RecyclerView recycler = ((RecyclerView) childView);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(activity, RecyclerView.VERTICAL, false);
                    recycler.setLayoutManager(layoutManager);
                    recycler.setAdapter(adapter);
                    break;
                }
            }
            if (!flag)
            {
                throw new RuntimeException("Multiple PopupWindow 布局不包含 RecyclerView,无法加载Adapter");
            }
        }

        //设置PopupWindow的宽
        public void setWidth(int width)
        {
            this.width = width;
        }

        //设置PopupWindow的高
        public void setHeight(int height)
        {
            this.height = height;
        }

        public View getView()
        {
            LinearLayout linearLayout = new LinearLayout(activity);
            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(MultipleSpinner.this.getWidth(), LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.setLayoutParams(param);
            linearLayout.setElevation(DensityUtil.dp2px(context, 10));
            RecyclerView recyclerView = new RecyclerView(activity);
            recyclerView.setBackgroundColor(0XFFEBEBEB);
            recyclerView.setLayoutParams(param);
            linearLayout.addView(recyclerView);
            view = linearLayout;
            return linearLayout;
        }

        private void initData()
        {

        }

        private void initEvent()
        {

        }


        public void showPopWindow(int offsetX, int offsetY)
        {
            //this.view.measure();
            int ParentViewHeight = MultipleSpinner.this.getHeight();
            int ParentViewWidth = MultipleSpinner.this.getWidth();


            this.getContentView().measure(0, 0);
            int popWeight = this.getContentView().getMeasuredWidth();
            int popHeight = this.getContentView().getMeasuredHeight();

            int windowHeight = getParentView().getHeight();

            if (offsetX > windowHeight * 0.8)
            {
                if ((popHeight + offsetY + ParentViewHeight) < windowHeight - 40)
                {
                    this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY + ParentViewHeight);
                    return;
                }

                if (popHeight >= offsetY)
                {
                    setHeight(offsetX);
                }
                this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY - popHeight);

                // todo 显示到文本框的上面

                return;
            }

            if (offsetY <= windowHeight * 0.8)
            {
                if ((popHeight + offsetY + ParentViewHeight) > windowHeight - 40)
                {
                    setHeight(windowHeight - offsetY - ParentViewHeight);
                }
                this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY + ParentViewHeight);
                return;
            }


            this.showAtLocation(getParentView(), Gravity.NO_GRAVITY, offsetX, offsetY + ParentViewHeight);
        }

        //寻找Activity的根布局
        public View getParentView()
        {
            //寻找Activity的根布局
            ViewGroup viewGroup = activity.findViewById(android.R.id.content);
            return viewGroup.getChildAt(0);
        }

    }

}
