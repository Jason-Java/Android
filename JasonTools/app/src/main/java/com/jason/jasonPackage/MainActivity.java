package com.jason.jasonPackage;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.serialport.IResultListener;
import com.jason.jasontools.serialport.ResultData;
import com.jason.jasontools.socket.SocketClient;
import com.jason.jasontools.util.LogUtil;
import com.jason.jasonuitools.view.JasonEditText;
import com.jason.jasonuitools.view.JasonSpinner;
import com.unite.sdk_net.user.Dept;
import com.unite.unite_base.UniteDB;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SocketClient socketClient = new SocketClient() {
            @Override
            protected String getIpAddress() {
                return "192.168.1.116";
            }

            @Override
            protected int getPort() {
                return 9600;
            }
        };

        findViewById(R.id.button).setOnClickListener(v -> {
            socketClient.open();
            byte[] bytes = new byte[10];
            int count = 0;
            bytes[count++] = (byte) 0xFA;
            bytes[count++] = (byte) 0x03;
            bytes[count++] = (byte) 0xE3;
            bytes[count++] = (byte) 0x01;
            bytes[count++] = (byte) 0xEE;
            bytes[count++] = (byte) 0xEE;
            bytes[count++] = (byte) 0xEE;
            bytes[count++] = (byte) 0xFF;
            bytes[count++] = (byte) 0xF0;
            bytes[count++] = (byte) 0xFE;
            IProtocol iProtocol = new IProtocol();
            iProtocol.setProtocol(bytes);
            socketClient.registerListener(new IResultListener<ResultData<IProtocol>>() {
                @Override
                public void onResult(ResultData<IProtocol>protocol) {
                    LogUtil.i("我是接收到的数据  " + protocol.getProtocol().getProtocolStr());
                    socketClient.disConnect();
                }
            });
            socketClient.sendData(iProtocol);
        });

        JasonSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, new String[]{"1", "2", "3"});
        spinner.setAdapter(adapter);


    }
}