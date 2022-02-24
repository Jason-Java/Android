package adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;

public class ViewHolder
{
    private final SparseArray<View> sparseArray;
    private View mConvertView;

    public ViewHolder(Context context, ViewGroup parent, int layoutId)
    {
        this.sparseArray=new SparseArray<>();
        mConvertView= LayoutInflater.from(context).inflate(layoutId,parent,false);
        mConvertView.setTag(this);
    }


    /**
     * 获取ViewHolder 如果convertView为空则重新实例化一个ViewHolder赋值给convertView,否则从已有的convertView中获取ViewHolder
     * @param context 上下文
     * @param position 位置
     * @param convertView convertView
     * @param parent 父布局控件
     * @param layoutId 布局id
     * @return
     */
    public static ViewHolder get(Context context,int position, View convertView, ViewGroup parent, int layoutId)
    {
        if (convertView == null)
        {
            return new ViewHolder(context,parent,layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 获取控件
     * @param viewId 控件的Id
     * @param <T>
     * @return
     */
    public <T extends View> T getView(@NonNull int viewId)
    {
        View view=sparseArray.get(viewId);
        if (view == null)
        {
            view = mConvertView.findViewById(viewId);
            sparseArray.put(viewId,view);
        }
        return (T) view;
    }


    /**
     *  获取convertView
     * @return
     */
    public View getConvertView()
    {
        return mConvertView;
    }

    /**
     * 设置TextView文本
     * @param viewId 控件id
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId,String text)
    {
        TextView view =getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * 设置字体颜色
     * @param viewId
     * @param colorId
     * @return
     */
    public ViewHolder setTextColor(int viewId,@ColorInt int colorId)
    {
        TextView view =getView(viewId);
        view.setTextColor(colorId);
        return this;
    }

    public ViewHolder setOnclickListener(int viewId,View.OnClickListener listener)
    {
        Button view=getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setImagerDrawable(int viewId,@DrawableRes int drawableId)
    {
        ImageView view=getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }

}
