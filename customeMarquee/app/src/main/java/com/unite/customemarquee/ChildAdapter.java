package com.unite.customemarquee;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.unite.customemarquee.base.BaseCommonRecyclerAdapter;
import com.unite.customemarquee.base.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class ChildAdapter extends BaseCommonRecyclerAdapter<String> {

    public ChildAdapter(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, int position) {
        holder.setText(R.id.childName, item.get(position));
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, int position) {
        View layout=holder.getView(R.id.llChild);
        layout.setOnClickListener(v->{
            Toast.makeText(activity, "child 我是第"+position+"组", Toast.LENGTH_SHORT).show();
        });
    }
}
