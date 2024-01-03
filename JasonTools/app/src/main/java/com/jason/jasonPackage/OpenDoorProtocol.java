package com.jason.jasonPackage;

import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.util.JasonVerifyUtil;

/**
 * <p>
 * 描述: 开门
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月21日
 */
public class OpenDoorProtocol extends IProtocol {
    public OpenDoorProtocol(int deviceNum) {
        byte[] bytes = new byte[6];
        int index = 0;
        bytes[index++] = (byte) 0x02;
        bytes[index++] = (byte) deviceNum;
        bytes[index++] = (byte)0xFF;
        bytes[index++] = 0x23;
        bytes[5] = (byte) 0x04;
        bytes[index++] = JasonVerifyUtil.crc8_Maxim(bytes, 1, 3);
        setProtocol(bytes);
    }
}
