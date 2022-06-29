package com.unite.jasonjar.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.unite.jasonjar.adapter.RecyclerViewHolder;
import com.unite.jasonjar.domain.KeyValue;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 多选框adapter
 */
public abstract class MultipleAdapter extends RecyclerView.Adapter<RecyclerViewHolder>
{

    protected ArrayList<KeyValue> item = new ArrayList<>();
    private LinkedList<Boolean> choice = new LinkedList<>();
    protected Activity activity;
    private int layoutId;
    private View view;
    private OnSelectItemListener onSelectItemListener;

    public void setOnSelectItemListener(OnSelectItemListener onSelectItemListener)
    {
        this.onSelectItemListener = onSelectItemListener;
    }

    public MultipleAdapter(Activity activity, int layoutId)
    {
        this.activity = activity;
        this.layoutId = layoutId;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        view = LayoutInflater.from(activity).inflate(layoutId, parent, false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount()
    {
        return item.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position)
    {
        setView(holder, item.get(position), position);
        Event(holder, item.get(position), position);
        itemStyle(holder, item.get(position), position);
        selectEvent(holder, item.get(position), position);
    }

    protected abstract void setView(@NonNull RecyclerViewHolder holder, KeyValue item, int position);

    protected abstract void Event(@NonNull RecyclerViewHolder holder, KeyValue item, int position);

    /**
     * 改变选中item的样式 可以重写此方法来更改选中的样式
     *
     * @param holder
     * @param item
     * @param position
     */
    private final void itemStyle(RecyclerViewHolder holder, KeyValue item, int position)
    {

        if (choice.get(position))
        {
            selectedStyle(holder, item, position);
        }
        else
        {
            noSelectedStyle(holder, item, position);
        }
    }

    //选中的样式
    public void  selectedStyle(RecyclerViewHolder holder, KeyValue item, int position){
        ViewGroup ParentView = (ViewGroup) holder.itemView;
        int size = ParentView.getChildCount();
        for (int i = 0; i < size; i++)
        {
            View childView = ParentView.getChildAt(i);
            if (childView instanceof TextView)
                holder.setTextColor(childView.getId(), 0XFF009fff);
        }
    }

    //未选中的样式
    public  void noSelectedStyle(RecyclerViewHolder holder, KeyValue item, int position)
    {
        ViewGroup ParentView = (ViewGroup) holder.itemView;
        int size = ParentView.getChildCount();
        for (int i = 0; i < size; i++)
        {
            View childView = ParentView.getChildAt(i);
            if (childView instanceof TextView)
                holder.setTextColor(childView.getId(), 0XFF000000);
        }

    }



    /**
     * 选中事件
     *
     * @param position
     */
    private void selectEvent(RecyclerViewHolder holder, KeyValue item, int position)
    {
        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                boolean flag = choice.get(position);
                choice.remove(position);
                if (flag) choice.add(position, false);
                else choice.add(position, true);
                MultipleAdapter.this.notifyItemChanged(position);
                if (onSelectItemListener != null)
                {
                    onSelectItemListener.select(getChoiceItem());
                }
            }
        });
    }


    /**
     * 获取选中的Item
     *
     * @return
     */
    public ArrayList<KeyValue> getChoiceItem()
    {
        ArrayList<KeyValue> list = new ArrayList<>();
        for (int i = 0; i < choice.size(); i++)
        {
            if (choice.get(i))
            {
                list.add(item.get(i));
            }
        }
        return list;
    }


    //设置数据
    public void setItem(ArrayList<KeyValue> i)
    {
        if (i != null && i.size() > 0)
        {
            item.clear();
            item.addAll(i);
            notifyDataSetChanged();
        }
        for (int j = 0; j < i.size(); j++)
        {
            choice.add(false);
        }
    }

    //添加数据
    public void addItem(ArrayList<KeyValue> i)
    {
        for (int j = 0; j < i.size(); j++)
        {
            item.add(i.get(j));
            notifyItemChanged(getItemCount() - 1);
        }
    }

    //清空所有的元素
    public void clearItem()
    {
        int count = getItemCount();
        item.clear();
        notifyItemRangeRemoved(0, count - 1);
    }

    /**
     * 删除某项元素
     *
     * @param position
     */
    public void removeItem(int position)
    {
        item.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 获取所有的元素
     *
     * @return
     */
    public List<KeyValue> getAllItem()
    {
        return item;
    }

    public interface OnSelectItemListener
    {
        void select(ArrayList<KeyValue> value);
    }
}
