package net;

import android.util.Log;


import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import retrofit2.HttpException;

public abstract class HttpObservable2<T> extends BaseObservable<T> {
    private Disposable d = null;
    private IShowDialog showDialog = null;
    private String message = null;
    private String TAG = "jason";
    private ArrayList<T> dataArray;

    //不提示加载框
    public HttpObservable2() {
        dataArray = new ArrayList<>();
    }

    //提示默认的加载框
    public HttpObservable2(IShowDialog showDialog) {
        dataArray = new ArrayList<>();
        this.showDialog = showDialog;
        this.message = "正在拼命加载中.....";
    }


    public HttpObservable2(IShowDialog showDialog, String message) {
        dataArray = new ArrayList<>();
        this.showDialog = showDialog;
        this.message = message;
    }

    //刚开始的时候执行
    @Override
    public final void onSubscribe(Disposable d) {
        this.d = d;
        if (showDialog != null) {
            showDialog.showWaitDialog(message);
        }
    }

    //成功的时候执行
    @Override
    public final void onNext(T value) {
        dataArray.add(value);
    }

    //失败的时候执行
    @Override
    public final void onError(Throwable e) {
        onFinal(e, null);
        destroy();
    }

    //结束执行
    @Override
    public final void onComplete() {
        destroy();
        success(dataArray);
    }

    //释放此次订阅
    private void destroy() {
        if (showDialog != null) {
            showDialog.closeWaitDialog();
        }

        if (!this.d.isDisposed()) {
            this.d.dispose();
            this.d = null;
        }

        if (showDialog != null) {
            showDialog = null;
        }
    }

    private void onFinal(Throwable e, String message) {
        int code = -1;
        if (e != null && e instanceof HttpException) {
            HttpException exception = (HttpException) e;
            code = exception.code();
        }

        if (interceptError(code, message)) {
            return;
        }
        if (e != null) {
            if (showDialog != null) {
                if (code == 401) {
                    showDialog.showErrorDialog("很抱歉,您登陆已超时,请重新登录!");
                } else if (code == 500) {
                    showDialog.showErrorDialog("服务器错误!");
                } else {
                    showDialog.showErrorDialog("网络连接超时!");
                }
            }
            Log.e(TAG, "HttpObservable-->onFinal-->e-->:" + e.getMessage());
        } else if (message != null) {
            if (showDialog != null) {
                showDialog.showErrorDialog("网络访问错误");
            }
            Log.e(TAG, message);
        } else {
            if (showDialog != null) {
                showDialog.showErrorDialog("网络访问错误");
            }
            Log.e(TAG, "onFinal: e,message 都为空");
        }
    }

    protected abstract void success(ArrayList<T> data);

    protected abstract boolean interceptError(int e, String message);
}
