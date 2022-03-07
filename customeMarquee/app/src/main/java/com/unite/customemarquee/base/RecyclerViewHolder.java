package com.unite.customemarquee.base;

import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewHolder extends RecyclerView.ViewHolder
{

    private SparseArray<View> viewSparseArray;

    public RecyclerViewHolder(@NonNull View itemView)
    {
        super(itemView);
        viewSparseArray = new SparseArray<>();
    }

    public <T extends View> T getView(int viewId)
    {
        View view = viewSparseArray.get(viewId);
        if (view == null)
        {
            view = itemView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }


    public void setText(int viewId,String text)
    {
        TextView view=getView(viewId);
        view.setText(text);
    }
    public void setTextColor(int viewId,@ColorInt int colorId)
    {
        TextView view=getView(viewId);
        view.setTextColor(colorId);
    }

    public void setBtnOnClickListener(int viewId, View.OnClickListener listener)
    {
        Button view=getView(viewId);
        view.setOnClickListener(listener);
    }

    public void setImage(int viewId, @DrawableRes int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
    }




}
