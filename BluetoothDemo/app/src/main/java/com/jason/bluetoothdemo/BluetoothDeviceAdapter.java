package com.jason.bluetoothdemo;

import android.app.Activity;
import android.bluetooth.le.ScanResult;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.jason.jasontools.adapter.BaseRecyclerAdapter;
import com.jason.jasontools.adapter.RecyclerViewHolder;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年09月08日
 */
public class BluetoothDeviceAdapter extends BaseRecyclerAdapter<ScanResult> {


    public BluetoothDeviceAdapter(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, ScanResult scanResult, int position) {
        holder.setText(R.id.bluetoothName, "设备名称：" + scanResult.getDevice().getName());
        holder.setText(R.id.mac, "mac " + scanResult.getDevice().getAddress());
        holder.setText(R.id.rssi, "RSSI：" + scanResult.getRssi() + "");
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, ScanResult scanResult, int position) {
        holder.setBtnOnClickListener(R.id.connect, v -> {
            ConnectDeviceActivity.scanResult = scanResult;
            Intent intent = new Intent(activity, ConnectDeviceActivity.class);
            activity.startActivity(intent);
        });
    }


    public void addItem(ScanResult scanResult) {
        int i = 0;
        for (; i < itemList.size(); i++) {
            if (itemList.get(i).getDevice().getAddress().equals(scanResult.getDevice().getAddress())) {
                break;
            }
        }
        if (i == itemList.size()) {
            itemList.add(scanResult);
            notifyDataSetChanged();
        }
    }
}
