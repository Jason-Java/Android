package com.jason.exampelrxjava;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Calendar;

import util.LogUtil;

public class AdapterItemDecorItem extends RecyclerView.ItemDecoration {

    private Paint paint;
    private Paint textPaint;

    public AdapterItemDecorItem() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(0Xff000000);
        paint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.WHITE);
        textPaint.setAntiAlias(true);
        textPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        if (parent.getAdapter() instanceof AdapterStar) {
            //获取当前屏幕有多少个Item
            int count = parent.getChildCount();
            int left = parent.getPaddingLeft();
            int right = parent.getWidth() - parent.getPaddingRight();
            for (int i = 0; i < count; i++) {
                View view = parent.getChildAt(i);
                AdapterStar adapterStar = (AdapterStar) parent.getAdapter();
                int currentPosition = parent.getChildAdapterPosition(view);
                int bottom = view.getTop();
                int top = parent.getPaddingTop();
                // 如果可见区域的第一个item超过recyclerView 的PaddingTop+吸顶样式的高度 则不绘制分割线
                if (view.getTop() <= top + 100)
                    continue;

                if (adapterStar.isHeadItem(currentPosition)) {
                    Rect rect = new Rect();
                    rect.set(left, bottom - 100, right, bottom);
                    c.drawRect(rect, paint);
                    String groupName = adapterStar.getAllItem().get(currentPosition).getGroupName() + "";
                    Rect textRect = new Rect();
                    textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                    textPaint.setTextSize(50);
                    c.drawText(groupName, left + 20, bottom - (50 - (textRect.height() / 2)), textPaint);
                } else {
                    c.drawRect(left, bottom - 1, right, bottom, textPaint);
                }
            }
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        if (parent.getAdapter() instanceof AdapterStar) {
            AdapterStar adapterStar = (AdapterStar) parent.getAdapter();
            //获取可见范围内的第一个Item索引位置
            int position = ((LinearLayoutManager) parent.getLayoutManager()).findFirstVisibleItemPosition();
            View currentView = parent.findViewHolderForAdapterPosition(position).itemView;


            int currentPosition = parent.getChildAdapterPosition(currentView);
            int nextPosition = currentPosition + 1;
            if (adapterStar.isHeadItem(nextPosition)) {
                int bottom = Math.min(100+parent.getPaddingTop(), currentView.getBottom());
                Rect rect = new Rect();
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                int top = parent.getPaddingTop();
                rect.set(left, top, right, bottom);
                c.drawRect(rect, paint);
                String groupName = adapterStar.getAllItem().get(currentPosition).getGroupName() + "";
                Rect textRect = new Rect();
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                textPaint.setTextSize(50);
                int y = bottom - (textRect.height() / 2);
                c.clipRect(left, top, right, y);
                c.drawText(groupName, left + 20,y, textPaint);
            } else {
                Rect rect = new Rect();
                int left = parent.getPaddingLeft();
                int right = parent.getWidth() - parent.getPaddingRight();
                int top = parent.getPaddingTop();
                rect.set(left, top, right, top + 100);
                c.drawRect(rect, paint);
                String groupName = adapterStar.getAllItem().get(currentPosition).getGroupName() + "";
                Rect textRect = new Rect();
                textPaint.getTextBounds(groupName, 0, groupName.length(), textRect);
                textPaint.setTextSize(50);
                c.drawText(groupName, left + 20, top + 50 + (textRect.height() / 2), textPaint);
            }

        }


    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        if (parent.getAdapter() instanceof AdapterStar) {
            AdapterStar adapterStar = (AdapterStar) parent.getAdapter();
            int currentPosition = parent.getChildAdapterPosition(view);

            if (adapterStar.isHeadItem(currentPosition)) {
                outRect.set(0, 100, 0, 0);
            } else {
                outRect.set(0, 1, 0, 0);
            }
        }
    }
}
