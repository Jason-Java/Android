package com.jason.invokeelectricitymeter.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import com.jason.invokeelectricitymeter.R;
import com.lotsrc.driver.IRemoteRequest;
import com.lotsrc.driver.IRemoteResponse;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private IRemoteRequest remoteRequest;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intent = new Intent();
        intent.setComponent(new ComponentName("com.unite.driver.dingshuo", "com.unite.driver.dingshuo.service.RemoteService"));
        //绑定服务
        findViewById(R.id.BindService).setOnClickListener(v -> {
            bindService(intent, connection, BIND_AUTO_CREATE);
        });
        //注册服务
        findViewById(R.id.registerServer).setOnClickListener(v -> {
            try {
                remoteRequest.registerCallBack(new IRemoteResponse.Stub() {
                    @Override
                    public void callBackValue(String tempValue) throws RemoteException {
                        System.out.println("MainActivity:" + tempValue);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        //发送消息
        findViewById(R.id.sendMessage).setOnClickListener(v -> {
            try {
                remoteRequest.mainToService(/*new ZRMessageRequest(发送的内容)*/);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            remoteRequest = IRemoteRequest.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };
}