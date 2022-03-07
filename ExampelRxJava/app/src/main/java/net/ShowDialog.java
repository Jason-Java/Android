package net;

import android.app.Activity;
import android.widget.Toast;



import util.LogUtil;


public class ShowDialog implements IShowDialog
{
    private Activity activity = null;

    public ShowDialog(Activity activity)
    {
        this.activity = activity;
    }

    @Override
    public void showWaitDialog(String msg)
    {
        if (activity != null)
        {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showErrorDialog(String msg)
    {
        if (activity != null)
        {
            Toast.makeText(activity, msg, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void closeWaitDialog()
    {
        if (activity != null)
        {
            Toast.makeText(activity, "关闭弹出框", Toast.LENGTH_SHORT).show();
        }
    }
}
