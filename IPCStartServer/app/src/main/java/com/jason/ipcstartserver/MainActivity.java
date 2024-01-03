package com.jason.ipcstartserver;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.os.RemoteException;

import androidx.appcompat.app.AppCompatActivity;

import com.jason.aidl.IMyAidlInterface;
import com.jason.aidl.ICallBackInterface;

public class MainActivity extends AppCompatActivity {

    private IMyAidlInterface aidlInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 绑定服务
        findViewById(R.id.start).setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.jason.ipcserver2", "com.jason.ipcserver2.server.MyService"));
            bindService(intent, connection, BIND_AUTO_CREATE);
        });
        //获取服务端回调数据
        findViewById(R.id.get).setOnClickListener(v -> {
            try {
                aidlInterface.setCallbacl(new ICallBackInterface.Stub() {
                    @Override
                    public void onCallBack(String msg) throws RemoteException {
                        System.out.println(msg);
                    }
                });
            } catch (Exception e) {
            }
        });
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            System.out.println("我被调用");
            aidlInterface = IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}