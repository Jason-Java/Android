package com.jason.bluetoothdemo;

import android.app.Activity;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.nfc.Tag;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jason.jasontools.util.LogUtil;
import com.jason.jasontools.util.StrUtil;

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
 * @createTime 2023年09月08日
 */
public class MyBluetoothGattCallback extends BluetoothGattCallback {
    private Activity activity;
    private ServiceAdapter adapter;
    private BluetoothGatt bluetoothGatt;
    /**
     * 添加服务
     */
    private final int ADD_SERVICE = 0X001;
    //修改连接状态
    private final int UPDATE_LINK_STATE = 0X002;

    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            Object obj = msg.obj;
            int what = msg.what;
            if (what == ADD_SERVICE) {
                addService((ArrayList<BluetoothGattService>) obj);
            } else if (what == UPDATE_LINK_STATE) {
                updateLinkState((Boolean) obj);
            }
        }

    };

    public MyBluetoothGattCallback(Activity activity) {
        this.activity = activity;
        adapter = new ServiceAdapter(activity, R.layout.service_adapter);
        ((RecyclerView) activity.findViewById(R.id.recyclerView)).setAdapter(adapter);
    }

    private static final String TAG = "MyBluetoothGattCallback";

    @Override
    public void onPhyUpdate(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
        super.onPhyUpdate(gatt, txPhy, rxPhy, status);

    }

    @Override
    public void onPhyRead(BluetoothGatt gatt, int txPhy, int rxPhy, int status) {
        super.onPhyRead(gatt, txPhy, rxPhy, status);
        LogUtil.i(TAG, "PHYRead status " + status);
    }

    @Override
    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        super.onConnectionStateChange(gatt, status, newState);
        boolean link = false;
        if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_CONNECTED) {
            link = true;
            LogUtil.i(TAG, "蓝牙连接成功");
        } else if (status == BluetoothGatt.GATT_SUCCESS && newState == BluetoothProfile.STATE_DISCONNECTED) {
            link = false;
            LogUtil.i(TAG, "蓝牙断开成功");
        }
        Message message = handler.obtainMessage();
        message.what = UPDATE_LINK_STATE;
        message.obj = link;
        handler.sendMessage(message);
        LogUtil.i(TAG, "status  " + status + "     newState   " + newState);
        this.bluetoothGatt = gatt;
    }

    @Override
    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        super.onServicesDiscovered(gatt, status);
        if (status == BluetoothGatt.GATT_SUCCESS) {
            List<BluetoothGattService> services = gatt.getServices();

            Message message = handler.obtainMessage();
            message.what = ADD_SERVICE;
            message.obj = services;
            handler.sendMessage(message);

            for (BluetoothGattService service : services) {
                LogUtil.i(TAG, "服务的UUID  " + service.getUuid().toString());
                LogUtil.i(TAG, "服务的类型： " + (service.getType() == 0 ? "主要的服务" : "次要的服务"));

                // 获取服务的特征值
                List<BluetoothGattCharacteristic> characteristics = service.getCharacteristics();
                for (BluetoothGattCharacteristic characteristic : characteristics) {
                    boolean bool = gatt.setCharacteristicNotification(characteristic, true);
                    if (bool) {
                        List<BluetoothGattDescriptor> descriptors = characteristic.getDescriptors();
                        for (BluetoothGattDescriptor description : descriptors) {
                            description.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                            gatt.writeDescriptor(description);
                        }
                    }
                    LogUtil.i(TAG, "\t\t特征值 UUID  " + characteristic.getUuid());
                    LogUtil.i(TAG, "\t\t特征值 属性  " + characteristic.getProperties());
                    byte[] value = characteristic.getValue();
                    if (value != null && value.length > 0) {
                        LogUtil.i(TAG, "\t\t特征值 Value " + StrUtil.byteToString(characteristic.getValue()));
                    }
                    //特征值的描述
                    LogUtil.i(TAG, "\t\t特征值 描述数量 " + characteristic.getDescriptors().size());
                    List<BluetoothGattDescriptor> descriptors = characteristic.getDescriptors();
                    for (BluetoothGattDescriptor description : descriptors) {
//                        description.get
                    }
                    LogUtil.i(TAG, "描述  " + characteristic.getDescriptors());
                }
            }
        }
    }

    @Override
    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicRead(gatt, characteristic, status);
        LogUtil.i(TAG, "CharacteristicRead " + StrUtil.byteToString(characteristic.getValue()));
    }

    @Override
    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        super.onCharacteristicWrite(gatt, characteristic, status);
        if (status == BluetoothGatt.GATT_SUCCESS) {
            LogUtil.i(TAG, "CharacteristicWrite " + StrUtil.byteToString(characteristic.getValue()));
        } else {
            LogUtil.i(TAG, "CharacteristicWrite " + "读取失败");
        }
    }

    @Override
    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        super.onCharacteristicChanged(gatt, characteristic);
        LogUtil.i(TAG, "CharacteristicChanged " + characteristic.getValue());
    }

    @Override
    public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        super.onDescriptorRead(gatt, descriptor, status);
        LogUtil.i(TAG, "DescriptorRead status " + status);
    }

    @Override
    public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        super.onDescriptorWrite(gatt, descriptor, status);
        LogUtil.i(TAG, "DescriptorWrite status " + status);
    }

    @Override
    public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
        super.onReliableWriteCompleted(gatt, status);
        LogUtil.i(TAG, "ReliableWriteCompleted status " + status);
    }

    @Override
    public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
        super.onReadRemoteRssi(gatt, rssi, status);
        if (status == BluetoothGatt.GATT_SUCCESS) {
            LogUtil.i(TAG, "RSSI  " + rssi);
        } else {
            LogUtil.e(TAG, "RSSI 读取失败");
        }
    }

    @Override
    public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
        super.onMtuChanged(gatt, mtu, status);
        LogUtil.i(TAG, "MtuChange   status " + status + "     mtu " + mtu);
    }

    @Override
    public void onServiceChanged(@NonNull BluetoothGatt gatt) {
        super.onServiceChanged(gatt);
    }


    /**
     * 添加服务
     *
     * @param services
     */
    private void addService(ArrayList<BluetoothGattService> services) {
        adapter.setItemList(services);
        adapter.setBluetoothGatt(bluetoothGatt);
    }

    private void updateLinkState(boolean bool) {
        Button button = activity.findViewById(R.id.connect);
        if (bool) {
            button.setText("断开");
        } else {
            button.setText("连接");
        }
    }
}
