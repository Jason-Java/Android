package com.jason.usb;

import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbDeviceConnection;
import android.hardware.usb.UsbEndpoint;
import android.hardware.usb.UsbInterface;
import android.hardware.usb.UsbManager;

import java.util.HashMap;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月20日
 */
public class UsbDeriveManage {

    private UsbManager usbManager = null;

    private HashMap<String, UsbDevice> usbDeviceMap = null;
    private HashMap<String, UsbInterface> usbInterfaceMap = null;
    private HashMap<String, UsbEndpoint> usbEndpointInMap = null;
    private HashMap<String, UsbEndpoint> usbEndpointOutMap = null;

    private UsbDeriveManage() {
        usbDeviceMap = new HashMap<>();
        usbInterfaceMap = new HashMap<>();
        usbEndpointInMap = new HashMap<>();
        usbEndpointOutMap = new HashMap<>();
    }

    private static class Singler {
        private static UsbDeriveManage usbDerive = new UsbDeriveManage();
    }

    public static UsbDeriveManage getInstance() {
        return Singler.usbDerive;
    }


    /**
     * 初始化打印机设备
     *
     * @param usbManager
     */
    public void init(UsbManager usbManager) {
        this.usbManager = usbManager;
        HashMap<String, UsbDevice> deviceList = usbManager.getDeviceList();
        //发现打印机
        for (String key : deviceList.keySet()) {
            if (deviceList.get(key).getVendorId() == 1305) {
                usbDeviceMap.put("1305", deviceList.get(key));
            }
            // todo 添加其他指定设备的打印机
        }
        if (usbDeviceMap.size() == 0) {
            System.err.println("未发现设备");
            return;
        }
        // 获取打印机的接口
        for (String key : usbDeviceMap.keySet()) {
            UsbInterface usbInterface = usbDeviceMap.get(key).getInterface(0);
            if (usbInterface.getEndpoint(0) != null) {
                usbEndpointInMap.put(key, usbInterface.getEndpoint(0));
            }
            if (usbInterface.getEndpoint(1) != null) {
                usbEndpointOutMap.put(key, usbInterface.getEndpoint(1));
            }
            usbInterfaceMap.put(key, usbInterface);
        }
    }

    /**
     * 发送指令
     * @param usbPrintBrand 打印机品牌
     * @param cmd 命令
     * @return
     */
    public int sendCmd(UsbPrintBrand usbPrintBrand, byte[] cmd) {
        UsbDevice usbDevice = usbDeviceMap.get(usbPrintBrand.getCode());
        if (!usbManager.hasPermission(usbDevice)) {
            System.out.println("未获取权限");
            return -1;
        }
        UsbDeviceConnection usbDeviceConnection = usbManager.openDevice(usbDevice);
        if (usbDeviceConnection == null || !usbDeviceConnection.claimInterface(usbInterfaceMap.get(usbPrintBrand.getCode()), true)) {
            return -1;
        }
        int redFlag = 0;
        synchronized (usbDevice) {
            try {
                redFlag = usbDeviceConnection.bulkTransfer(usbEndpointInMap.get(usbPrintBrand.getCode()), cmd, cmd.length, 1000);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                usbDeviceConnection.close();
            }
            return redFlag;
        }
    }


}
