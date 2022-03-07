package net;

import android.app.Activity;

public interface IShowDialog
{
    //等待对话框
    void showWaitDialog(String msg);

    //错误对话框
    void showErrorDialog(String msg);

    //关闭等待对话框
    void closeWaitDialog();
}
