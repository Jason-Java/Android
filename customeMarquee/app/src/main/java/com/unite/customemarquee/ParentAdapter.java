package com.unite.customemarquee;

import android.app.Activity;
import android.view.View;

import androidx.annotation.NonNull;

import com.unite.customemarquee.base.BaseCommonRecyclerAdapter;
import com.unite.customemarquee.base.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.HashMap;

public class ParentAdapter extends BaseCommonRecyclerAdapter<HashMap<String, ArrayList<String>>> {

    public ParentAdapter(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, int position) {
        holder.setText(R.id.name,"组");

    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, int position) {
        View pa = holder.getView(R.id.llLayout);
        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
