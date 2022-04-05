package com.unite.customemarquee;

import android.app.Activity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView childRecycler = holder.getView(R.id.childRecycler);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(activity,1);
        childRecycler.setLayoutManager(gridLayoutManager);
        ChildAdapter childAdapter=new ChildAdapter(activity,R.layout.child_adapter);
        childAdapter.setItem(item.get(position).get("组"));
        childRecycler.setAdapter(childAdapter);
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, int position) {
        View pa = holder.getView(R.id.llLayout);

        pa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView childRecycler = holder.getView(R.id.childRecycler);
                if(childRecycler.getVisibility()==View.GONE)
                {
                    childRecycler.setVisibility(View.VISIBLE);
                }else{
                    childRecycler.setVisibility(View.GONE);
                }

                Toast.makeText(activity, "我点击的是第"+position+"组", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
