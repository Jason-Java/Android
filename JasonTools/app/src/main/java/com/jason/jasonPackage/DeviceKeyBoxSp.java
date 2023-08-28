package com.jason.jasonPackage;

import com.jason.jasontools.serialport.DeviceSerialPort;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月21日
 */
public class DeviceKeyBoxSp extends DeviceSerialPort {
    private static final String TAG = "DeviceKeyBoxSp";

    private static class Single {
        private static DeviceKeyBoxSp instance = new DeviceKeyBoxSp();
    }

    public static DeviceKeyBoxSp getInstance() {
        return Single.instance;
    }
}
