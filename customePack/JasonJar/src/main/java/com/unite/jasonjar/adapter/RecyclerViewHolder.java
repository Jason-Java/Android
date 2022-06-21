package com.unite.jasonjar.adapter;

import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unite.jasonjar.R;

public class RecyclerViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> viewSparseArray;

    public RecyclerViewHolder(@NonNull View itemView) {
        super(itemView);
        viewSparseArray = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId) {
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }


    public void setText(int viewId, String text) {
        TextView view = getView(viewId);
        view.setText(text);
    }

    /**
     * 获取文字
     * @param viewId
     * @return
     */
    public String getText(int viewId) {
        TextView view = getView(viewId);
        return view.getText().toString();
    }

    /**
     * 设置文字颜色
     * @param viewId
     * @param colorId
     */
    public void setTextColor(int viewId, @ColorInt int colorId) {
        TextView view = getView(viewId);
        view.setTextColor(colorId);
    }

    /**
     * 设置背景颜色
     * @param viewId
     * @param drawable
     */
    public void setBackGround(int viewId, Drawable drawable) {
        View view = getView(viewId);
        view.setBackground(drawable);
    }

    /**
     * 设置是否可见
     * @param viewId
     * @param visibility
     */
    public void setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
    }

    /**
     * 设置点击事件
     * @param viewId
     * @param listener
     */
    public void setViewOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    /**
     * 设置图片
     * @param viewId
     * @param drawableId
     */
    public void setImage(int viewId, @DrawableRes int drawableId) {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
    }

    /**
     * 设置焦点改变事件
     * @param viewId
     * @param listener
     */
    public void setFocusChangeListener(int viewId, View.OnFocusChangeListener listener) {
        View view = getView(viewId);
        view.setOnFocusChangeListener(listener);
    }

}
