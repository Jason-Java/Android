package com.jason.jasontools.serialport;

import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.util.LogUtil;
import com.jason.jasontools.util.StrUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 描述: 设备串口初始化类
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月16日
 */
public abstract class DeviceSerialPort {
    private final static String TAG = "JasonBaseSerialPortTag";
    private SerialPortUtil serialPortUtil = null;
    private Map<String, IResultListener> listenerMap = new HashMap<>();

    private IVerifySerialProtocolData verifySerialProtocolData = null;
    private IParseSerialProtocolData parseSerialProtocolData = null;


    /**
     * 打开串口接收器
     *
     * @param serialPortName 串口名称
     * @param baudrate       波特率
     */
    public void open(String serialPortName, int baudrate) {
        this.open(serialPortName, baudrate, 0);
    }

    /**
     * 打开串口接收器
     *
     * @param serialPortName 串口名称
     * @param baudrate       波特率
     * @param flags          标志位
     */
    public void open(String serialPortName, int baudrate, int flags) {
        try {
            this.serialPortUtil = new SerialPortUtil(serialPortName, baudrate, flags);
            this.serialPortUtil.registerListener(serialPortListener);
        } catch (Exception e) {
            LogUtil.e(TAG, "open serialPort error " + e.getMessage());
        }
    }

    /**
     * 注册监听器
     *
     * @param tag      监听器标识唯一标识,如果已经存在则会覆盖
     * @param listener
     */
    public void registerListener(String tag, IResultListener listener) {
        listenerMap.put(tag, listener);
    }

    /**
     * 注册监听器
     *
     * @param listener 监听器
     */
    public void registerListener(IResultListener listener) {
        registerListener(listener.getTAG(), listener);
    }

    /**
     * 取消监听器
     *
     * @param tag 监听器标识唯一标识
     */
    public void unregisterListener(String tag) {
        listenerMap.remove(tag);
    }

    /**
     * 取消监听器
     *
     * @param listener 监听器
     */
    public void unregisterListener(IResultListener listener) {
        unregisterListener(listener.getTAG());
    }

    public void unregisterAllListener() {
        listenerMap.clear();
    }

    /**
     * 设置校验数据的接口
     *
     * @param verifySerialProtocolData 校验数据的接口
     */
    public void setVerifySerialProtocolData(IVerifySerialProtocolData verifySerialProtocolData) {
        this.verifySerialProtocolData = verifySerialProtocolData;
    }

    /**
     * 解析数据
     *
     * @param parseSerialProtocolData 解析数据的接口
     */
    public void setParseSerialProtocolData(IParseSerialProtocolData parseSerialProtocolData) {
        this.parseSerialProtocolData = parseSerialProtocolData;
    }

    /**
     * 发送数据
     *
     * @param protocol 协议
     */
    public void sendData(IProtocol protocol) {
        sendData((IProtocol) protocol.clone(), null);
    }


    /**
     * 发送数据<br/>
     * 在发送数据之前如果{@link  #verifySerialProtocolData}不为空
     * 则会调用{@link IVerifySerialProtocolData#verifySendData(IProtocol, int)} 的方法进行校验数据
     * 校验数据规则由用户自行决定
     *
     * @param protocol    协议
     * @param listenerTag 监听器标识唯一标识
     */
    public void sendData(IProtocol protocol, String listenerTag) {
        IResultListener iResultListener = listenerMap.get(listenerTag);
        try {
            if (this.verifySerialProtocolData != null) {
                protocol = verifySerialProtocolData.verifySendData(protocol, protocol.getProtocol().length);
            }
            serialPortUtil.sendCmd(protocol);
        } catch (VerifyFailedException e) {
            LogUtil.e(TAG, "发送数据不符合协议");
            if (StrUtil.isNotEmpty(listenerTag) && iResultListener != null) {
                iResultListener.error("发送数据不符合协议"+ StrUtil.hexToString(protocol.getProtocol()));
            }
        } catch (IOException e) {
            LogUtil.e(TAG, "发送数据失败");
            if (iResultListener != null)
                iResultListener.error("发送数据失败");
        } catch (NullPointerException e) {
            LogUtil.e(TAG, "串口未打开");
            if (iResultListener != null)
                iResultListener.error("串口未打开");
        }
    }

    /**
     * 销毁
     */
    public void onDestroy() {
        unregisterAllListener();
        serialPortListener = null;
        if (serialPortUtil != null) {
            serialPortUtil.close();
        }
        serialPortUtil = null;
    }

    /**
     * 串口接收器
     */
    private ISerialPortListener serialPortListener = new ISerialPortListener() {
        @Override
        public void onResponseData(byte[] data, int length) {
            IProtocol protocol = new IProtocol();
            protocol.setProtocol(data);
            try {
                if (verifySerialProtocolData != null)
                    protocol = verifySerialProtocolData.verifyReceiveData(protocol, length);
                if (parseSerialProtocolData != null)
                    protocol = parseSerialProtocolData.parseData(protocol, length);
                for (IResultListener listener : listenerMap.values())
                    listener.onResult(protocol);
            } catch (VerifyFailedException e) {
                LogUtil.e(TAG, "verifyData error " + e.getMessage());
            } catch (Exception e) {
                LogUtil.e(TAG, "parseData error " + e.getMessage());
            }
        }
    };
}
