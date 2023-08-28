package com.jason.jasontools.serialport;

import com.jason.jasontools.commandbus.IProtocol;

/**
 * <p>
 * 描述: 解析串口返回过来的数据抽象类
 * </P>
 */
public interface IParseSerialProtocolData {
    /**
     * 解析串口返回过来的数据
     *
     * @param protocol 协议
     * @param len 协议数据长度
     * @return
     */
    public IProtocol parseData(IProtocol protocol, int len);
}
