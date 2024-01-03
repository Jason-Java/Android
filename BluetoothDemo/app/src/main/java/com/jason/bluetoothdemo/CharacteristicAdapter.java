package com.jason.bluetoothdemo;

import android.app.Activity;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.SystemClock;

import androidx.annotation.NonNull;

import com.jason.jasontools.adapter.BaseRecyclerAdapter;
import com.jason.jasontools.adapter.RecyclerViewHolder;
import com.jason.jasontools.util.LogUtil;

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
public class CharacteristicAdapter extends BaseRecyclerAdapter<BluetoothGattCharacteristic> {
    private BluetoothGatt bluetoothGatt;

    public CharacteristicAdapter(Activity activity, int layoutId) {
        super(activity, layoutId);
    }

    public void setBluetoothGatt(BluetoothGatt bluetoothGatt) {
        this.bluetoothGatt = bluetoothGatt;
    }

    @Override
    protected void setView(@NonNull RecyclerViewHolder holder, BluetoothGattCharacteristic characteristic, int position) {
        holder.setText(R.id.characteristicName, "我是特征值的名称");
        holder.setText(R.id.characteristicUUID, "UUID：" + characteristic.getUuid());
        holder.setText(R.id.characteristicProperties, "特征值的属性：" + characteristic.getProperties());


    }

    @Override
    protected void Event(@NonNull RecyclerViewHolder holder, BluetoothGattCharacteristic characteristic, int position) {
        holder.setBtnOnClickListener(R.id.sendCmd, v -> {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    bluetoothGatt.setCharacteristicNotification(characteristic, true);


                    characteristic.setValue(new byte[]{(byte)0xF2,(byte)0x02,0x11,0x12});
                    characteristic.setWriteType(0x02);
                   LogUtil.i("发送是否成功  "+ bluetoothGatt.writeCharacteristic(characteristic));
                }
            }).start();

        });
        holder.setBtnOnClickListener(R.id.receiveCmd, v -> {

            LogUtil.i("接受状态"+bluetoothGatt.readCharacteristic(characteristic));
        });
    }
}
