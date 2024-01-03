package com.jason.jnitest;

import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.serialport.DeviceSerialPort;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月17日
 */
public class DeriveKeyBoxSp extends DeviceSerialPort {


    private static class SingletonHolder {
        private static final DeriveKeyBoxSp INSTANCE = new DeriveKeyBoxSp();
    }

    public static DeriveKeyBoxSp getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 校验接受或发送的数据是否符合协议
     *
     * @param data   接受或发送的数据
     * @param length
     * @return true 符合协议 false 不符合协议
     */
    @Override
    public boolean verifyData(byte[] data, int length) {
        return true;
    }

    /**
     * 解析数据
     *
     * @param data   接受到的数进行解析
     * @param length
     * @return 返回解析后的协议
     */
    @Override
    public IProtocol parseData(byte[] data, int length) {
        return new IProtocol();
    }
}
