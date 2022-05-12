package com.unite.customepack;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class FlowLayout extends ViewGroup {

    ArrayList<ArrayList<View>> allView;
    ArrayList<Integer> heightEachLine;


    public FlowLayout(Context context) {
        super(context);
        // init();
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init();
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void init() {
        allView = new ArrayList<>();
        heightEachLine = new ArrayList<>();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        init();

        int parentWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int parentHeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);

        int userWidth = 0;
        int totalHeight = 0;
        int totalWidth = 0;
        int lineHeightMax = 0;

        ArrayList<View> lineView = new ArrayList<>();

        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = this.getChildAt(i);
            LayoutParams layoutParams = childView.getLayoutParams();

            int parentLeftAndRightPadding = this.getPaddingLeft() + this.getPaddingRight();
            int parentTopAndBottomPadding = this.getPaddingBottom() + this.getPaddingTop();
            int childMeasureWidthSpec = getChildMeasureSpec(widthMeasureSpec, parentLeftAndRightPadding, layoutParams.width);
            int childMeasureHeightSpec = getChildMeasureSpec(heightMeasureSpec, parentTopAndBottomPadding, layoutParams.height);


            childView.measure(childMeasureWidthSpec, childMeasureHeightSpec);

            int childHeight = childView.getMeasuredHeight();
            int childWidth = childView.getMeasuredWidth();

            userWidth += childWidth;
            lineHeightMax = Math.max(lineHeightMax, childHeight);
            if (userWidth + parentLeftAndRightPadding >= parentWidth) {
                allView.add(lineView);
                lineView = new ArrayList<>();
                heightEachLine.add(lineHeightMax);
                totalWidth = Math.max(totalHeight, userWidth);
                totalHeight += lineHeightMax;
                userWidth = 0;
                lineHeightMax = 0;
            }
            lineView.add(childView);

        }
        heightEachLine.add(lineHeightMax);
        allView.add(lineView);
        totalHeight += lineHeightMax;

        totalHeight = parentHeightMode == MeasureSpec.EXACTLY ? totalHeight : parentHeight;
        totalWidth = parentWidthMode == MeasureSpec.EXACTLY ? totalWidth : parentWidth;

        setMeasuredDimension(parentWidth, totalHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int parentPaddingLeft = this.getPaddingLeft();
        int parentPaddingTop = this.getPaddingTop();
        int left, top;
        left = parentPaddingLeft;
        top = parentPaddingTop;

        for (int i = 0; i < allView.size(); i++) {
            ArrayList<View> lineView = allView.get(i);
            for (View child : lineView) {
                int right, bottom;
                right = left + child.getMeasuredWidth();
                bottom = top + child.getMeasuredHeight();
                child.layout(left, top, right, bottom);
                left = right;
            }
            top += heightEachLine.get(i);
            left = parentPaddingLeft;
        }
    }
}
