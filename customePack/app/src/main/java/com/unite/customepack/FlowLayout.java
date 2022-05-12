package com.unite.customepack;

import android.content.Context;
import android.text.Layout;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class FlowLayout extends ViewGroup
{

    ArrayList<ArrayList<View>> allView = new ArrayList<>();
    ;
    ArrayList<Integer> heightEachLine = new ArrayList<>();


    public FlowLayout(Context context)
    {
        super(context);

    }

    public FlowLayout(Context context, AttributeSet attrs)
    {
        super(context, attrs);

    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    private void init()
    {
        allView.clear();
        heightEachLine.clear();

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
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
        for (int i = 0; i < childCount; i++)
        {
            View childView = this.getChildAt(i);
            if(childView.getVisibility()==View.GONE)
                continue;

            final LayoutParams layoutParams = (LayoutParams) childView.getLayoutParams();

            int parentLeftAndRightPadding = this.getPaddingLeft() + this.getPaddingRight();
            int parentTopAndBottomPadding = this.getPaddingBottom() + this.getPaddingTop();
            int childMeasureWidthSpec = getChildMeasureSpec(widthMeasureSpec, parentLeftAndRightPadding, layoutParams.width);
            int childMeasureHeightSpec = getChildMeasureSpec(heightMeasureSpec, parentTopAndBottomPadding, layoutParams.height);


            childView.measure(childMeasureWidthSpec, childMeasureHeightSpec);

            int childHeight = childView.getMeasuredHeight();
            int childWidth = childView.getMeasuredWidth();
            int childMarginTop = layoutParams.topMargin;
            int childMarginLeft = layoutParams.leftMargin;
            int childMarginRight = layoutParams.rightMargin;
            int childMarginBottom = layoutParams.bottomMargin;


            //计算当前View所占的宽度(包括左右外边距)
            int currentWidth = childWidth + childMarginLeft + childMarginRight;
            //计算当前VIew所占的高度(包括上下外边距)
            int currentHeight = childHeight + childMarginTop + childMarginBottom;
            if (currentWidth + userWidth + parentLeftAndRightPadding >= parentWidth)
            {
                allView.add(lineView);
                lineView = new ArrayList<>();
                heightEachLine.add(lineHeightMax);
                totalWidth = Math.max(totalHeight, userWidth);
                totalHeight += lineHeightMax;
                userWidth = 0;
                lineHeightMax = 0;
            }
            userWidth += currentWidth;
            lineHeightMax = Math.max(lineHeightMax, currentHeight);
            lineView.add(childView);

        }
        heightEachLine.add(lineHeightMax);
        allView.add(lineView);
        totalHeight += lineHeightMax;

        totalHeight = parentHeightMode == MeasureSpec.EXACTLY ? totalHeight : parentHeight;
        totalWidth = parentWidthMode == MeasureSpec.EXACTLY ? totalWidth : parentWidth;

        setMeasuredDimension(totalWidth, totalHeight);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b)
    {
        int parentPaddingLeft = this.getPaddingLeft();
        int parentPaddingTop = this.getPaddingTop();
        int left, top;
        left = parentPaddingLeft;
        top = parentPaddingTop;


        for (int i = 0; i < allView.size(); i++)
        {
            ArrayList<View> lineView = allView.get(i);
            for (View child : lineView)
            {
                int right, bottom;

                if(child.getVisibility()==View.GONE)
                    continue;


                //计算子View的外边距
                LayoutParams layoutParams = (LayoutParams) child.getLayoutParams();
                int childMarginTop = layoutParams.topMargin;
                int childMarginLeft = layoutParams.leftMargin;
                int childMarginRight = layoutParams.rightMargin;
                int childMarginBottom = layoutParams.bottomMargin;

                int realLeft = left + childMarginLeft;
                int realTop = top + childMarginTop;

                right = left + childMarginRight + child.getMeasuredWidth();
                bottom = top + childMarginBottom + child.getMeasuredHeight();
                child.layout(realLeft, realTop, right, bottom);
                left = right;

            }
            top += heightEachLine.get(i);
            left = parentPaddingLeft;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {

        return false;
    }

    ////////////////////////////////////////////////////////
    // 以下函数和类必须要有不然计算不了子View的Margin
    ///////////////////////////////////////////////////////

    @Override
    protected ViewGroup.LayoutParams generateDefaultLayoutParams()
    {
        return new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @Override
    protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams p)
    {
        return new LayoutParams(p);
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs)
    {
        return new LayoutParams(getContext(), attrs);
    }


    @Override
    protected boolean checkLayoutParams(ViewGroup.LayoutParams p)
    {
        return p instanceof LayoutParams;
    }

    public static class LayoutParams extends ViewGroup.MarginLayoutParams
    {


        public LayoutParams(Context c, AttributeSet attrs)
        {
            super(c, attrs);
        }

        public LayoutParams(int width, int height)
        {
            super(width, height);
        }

        public LayoutParams(MarginLayoutParams source)
        {
            super(source);
        }

        public LayoutParams(ViewGroup.LayoutParams source)
        {
            super(source);
        }
    }
}
