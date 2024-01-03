package com.jason.bluetoothdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

import com.jason.bluetoothdemo.databinding.ActivityMainBinding;
import com.jason.jasontools.util.LogUtil;

import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ActivityMainBinding binding;
    private BluetoothDeviceAdapter adapter;
    private BluetoothAdapter bluetoothAdapter;

    private HashMap<String, BluetoothDevice> macAndDeviceMap = new HashMap<>();
    private MyScanCallback scanResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initData();
        initEvent();


    }

    private void initData() {
        initRecyclerView();
        scanResult = new MyScanCallback();
        if (!ApplyPermission.apply(this)) {
            return;
        }
        if (!checkSupportBLE(this)) {
            LogUtil.e(TAG, "设备不支持低功率蓝牙");
            return;
        }
        if (!checkBluetoothEnable(this)) {
            LogUtil.e(TAG, "蓝牙未打开");
            BluetoothAdapter.getDefaultAdapter().enable();
        }
        scanBluetoothDevice(this);
    }

    /**
     * 初始化RecyclerView
     */
    private void initRecyclerView() {
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recycler.setLayoutManager(manager);
        binding.recycler.setHasFixedSize(true);
        adapter = new BluetoothDeviceAdapter(this, R.layout.bluetooth_device_adapter);
        binding.recycler.setAdapter(adapter);

    }

    private void initEvent() {

    }

    /**
     * 检查设备是否支持蓝牙低功率
     *
     * @param context 上下文环境变量
     * @return true 支持， false 不支持
     */
    private boolean checkSupportBLE(Context context) {
        PackageManager packageManager = context.getPackageManager();
        return packageManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE);
    }

    /**
     * 检查设备蓝牙是否可用
     *
     * @param context 上下文环境变量
     * @return true 可用，false 不可用
     */
    private boolean checkBluetoothEnable(Context context) {
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled()) {
            return false;
        }
        return true;
    }


    private void scanBluetoothDevice(Context context) {
        bluetoothAdapter.getBluetoothLeScanner().startScan(scanResult);
    }

    private void stopScanBluetoothDevice() {
        bluetoothAdapter.getBluetoothLeScanner().stopScan(scanResult);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000 && permissions.length == ApplyPermission.permission.length) {
            LogUtil.i(TAG, "动态权限申请成功");
        } else {
            LogUtil.e(TAG, "动态权限申请失败");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private  class MyScanCallback extends ScanCallback {
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            MainActivity.this.adapter.addItem(result);

            /*LogUtil.i(TAG, "设备名称" + result.getDevice().getName());
            LogUtil.i(TAG, "mac " + result.getDevice().getAddress());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                LogUtil.i(TAG, "设备别名 " + result.getDevice().getAlias());
            }
            LogUtil.i(TAG, "设备绑定状态  " + result.getDevice().getBondState());
            LogUtil.i(TAG, "设备UUID  " + result.getDevice().getUuids());*/

        }

        @Override
        public void onScanFailed(int errorCode) {
            super.onScanFailed(errorCode);
            LogUtil.e(TAG, "扫描设备失败");
        }

        @Override
        public void onBatchScanResults(List<ScanResult> results) {
            super.onBatchScanResults(results);
            LogUtil.i(TAG,"批量扫描设备结果");
        }
    }
}
