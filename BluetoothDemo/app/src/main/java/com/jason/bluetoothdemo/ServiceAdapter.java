package com.jason.bluetoothdemo;

import android.app.Activity;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jason.jasontools.adapter.BaseRecyclerAdapter;
import com.jason.jasontools.adapter.RecyclerViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年09月11日
 */
public class ServiceAdapter extends BaseRecyclerAdapter<BluetoothGattService> {
    private BluetoothGatt bluetoothGatt;

    public ServiceAdapter(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    public void setBluetoothGatt(BluetoothGatt bluetoothGatt) {
        this.bluetoothGatt = bluetoothGatt;
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, BluetoothGattService service, int position) {
        holder.setText(R.id.serviceName, "我是服务名称");
        holder.setText(R.id.serviceUUID, "uuid：" + service.getUuid());
        holder.setText(R.id.serviceType, "服务类型：" + (service.getType() == 0 ? "主要的服务" : "次要的服务"));
        List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
        if (characteristics.size() > 0) {
            RecyclerView recycler = holder.getView(R.id.recyclerView);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
            recycler.setLayoutManager(linearLayoutManager);
            CharacteristicAdapter adapter = new CharacteristicAdapter(activity, R.layout.characteristic_adapter);
            adapter.setItemList((ArrayList<BluetoothGattCharacteristic>) characteristics);
            adapter.setBluetoothGatt(bluetoothGatt);
            recycler.setAdapter(adapter);
        }
    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, BluetoothGattService service, int position) {

    }
}
