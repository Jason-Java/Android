package com.jason.jasonPackage;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.jason.jasontools.commandbus.IMessageListener;
import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.util.CrcVerify;
import com.jason.jasontools.util.LogUtil;
import com.jason.jasontools.util.StrUtil;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DeviceKeyBoxSp instance = DeviceKeyBoxSp.getInstance();
        instance.open("/dev/ttyS3", 19200, 0);
        instance.setVerifySerialProtocolData(new VerifyData());
        LogUtil.addFilterTag("CommandExecuteCenter");
        new Thread(SendCommandCenterSingle.getInstance()).start();
        findViewById(R.id.button).setOnClickListener(v -> {
            KeyBoxCommand keyBoxCommand = new KeyBoxCommand(new IMessageListener<IProtocol>() {
                @Override
                public void success(IProtocol protocol) {
                    LogUtil.i("MainActivity", "success: " + protocol.toString());
                }

                @Override
                public void error(String s, int i) {
                    LogUtil.i("MainActivity", "error: " + s + "  " + i);
                }
            }, new OpenDoorProtocol(1));
            SendCommandCenterSingle.getInstance().addQueue(keyBoxCommand);
        });
        OpenDoorProtocol openDoor = new OpenDoorProtocol(1);
        openDoor.addProtocol(new OpenDoorProtocol(2).getProtocol());
        LogUtil.e("MainActivity", "onCreate: " + StrUtil.byteToString(openDoor.getProtocol()));
    }
}