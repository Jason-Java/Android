package com.jason.bluetoothdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.le.ScanResult;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jason.bluetoothdemo.databinding.ActivityConnectDeviceBinding;

public class ConnectDeviceActivity extends AppCompatActivity {

    private ActivityConnectDeviceBinding binding;
    public static ScanResult scanResult;
    private BluetoothGatt bluetoothGatt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityConnectDeviceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        iniEvent();
    }


    private void initData() {
        bluetoothGatt = scanResult.getDevice().connectGatt(this, false, new MyBluetoothGattCallback(this));
        initRecyclerView();

    }

    private void initRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        binding.recyclerView.setLayoutManager(linearLayoutManager);
        binding.recyclerView.setHasFixedSize(true);

    }

    private void iniEvent() {
        binding.connect.setOnClickListener(v -> {
            Button button= (Button) v;
            if (button.getText().toString().equals("断开")) {
                bluetoothGatt.disconnect();
            } else if (button.getText().toString().equals("连接")) {
                bluetoothGatt.connect();
            }
        });
        //发现服务
        binding.findService.setOnClickListener(v -> {
            bluetoothGatt.discoverServices();
        });
        //读取RSSI
        binding.readRSSI.setOnClickListener(v -> {
            bluetoothGatt.readRemoteRssi();
        });
       /* // 发送数据
        binding.sendData.setOnClickListener(v -> {
         bluetoothGatt.
        });*/
    }
}