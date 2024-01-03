package com.jason.ipcserver2.server;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;

import com.jason.aidl.ICallBackInterface;
import com.jason.aidl.IMyAidlInterface;

public class MyService extends Service {

    /**
     * 自定义binder 继承自aidl IMyAidlInterface.Stub
     */
    private MyBinder binder;
    /**
     * 自定义aidl回调接口
     */
    private ICallBackInterface iCallBackInterface;

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        //返回自定义的binder
        return binder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        binder = new MyBinder();
        System.out.println("创建服务");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        System.out.println("启动服务");
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (iCallBackInterface != null) {
                        try {
                            iCallBackInterface.onCallBack("我是服务端，我在不停的发送消息");
                            SystemClock.sleep(1000);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("销毁服务");
    }

    /**
     * 自定义binder 继承自aidl IMyAidlInterface.Stub,并且实现aidl中的方法
     */
    public class MyBinder extends IMyAidlInterface.Stub {

        @Override
        public String getName() {
            return "jason";
        }

        @Override
        public void setName(String name) throws RemoteException {

        }

        @Override
        public void setCallbacl(ICallBackInterface callBack) throws RemoteException {
            iCallBackInterface = callBack;
            callBack.onCallBack("注册成功");
        }
    }
}