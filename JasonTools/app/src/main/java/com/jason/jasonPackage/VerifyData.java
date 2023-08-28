package com.jason.jasonPackage;

import com.jason.jasontools.commandbus.IProtocol;
import com.jason.jasontools.serialport.IVerifySerialProtocolData;
import com.jason.jasontools.serialport.VerifyFailedException;
import com.jason.jasontools.util.CrcVerify;
import com.jason.jasontools.util.LogUtil;
import com.jason.jasontools.util.StrUtil;

/**
 * <p>
 * 描述: 校验串口发送和接口的数据
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月22日
 */
public class VerifyData implements IVerifySerialProtocolData {
    private static final String TAG = "VerifyDataTAG";

    @Override
    public IProtocol verifySendData(IProtocol protocol, int i) throws VerifyFailedException {
        //发送校验
        if (protocol.getProtocol()[0] == 0x02 && protocol.getProtocol()[5] == 0x04) {
            if (protocol.getProtocol().length < 6) {
                throw new VerifyFailedException("数据长度不足");
            }
            return sendVerifyData(protocol, i);
        }

        throw new VerifyFailedException("数据格式错误");
    }


    private IProtocol sendVerifyData(IProtocol protocol, int i) throws VerifyFailedException {
        if (CrcVerify.crc8_Maxim(protocol.getProtocol(), 1, 3) != protocol.getProtocol()[4]) {
            throw new VerifyFailedException("crc校验失败");
        }
        byte[] bytes = new byte[10];
        int index = 0;
        bytes[index++] = protocol.getProtocol()[0];
        //箱子地址
        String hex = StrUtil.byteToString(protocol.getProtocol()[1]);
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(0, 1), 16));
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(1, 2), 16));
        //钥匙地址
        hex = StrUtil.byteToString(protocol.getProtocol()[2]);
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(0, 1), 16));
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(1, 2), 16));
        //命令号
        hex = StrUtil.byteToString(protocol.getProtocol()[3]);
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(0, 1), 16));
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(1, 2), 16));
        //校验
        hex = StrUtil.byteToString(protocol.getProtocol()[4]);
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(0, 1), 16));
        bytes[index++] = (byte) (0x30 + Integer.parseInt(hex.substring(1, 2), 16));
        //结束
        bytes[index++] = protocol.getProtocol()[5];
        protocol.setProtocol(bytes);
        return protocol;
    }

    @Override
    public IProtocol verifyReceiveData(IProtocol protocol, int i) throws VerifyFailedException {
        //接收校验
        if (protocol.getProtocol()[0] == 0x01 && protocol.getProtocol()[i - 1] == 0x03) {
            if (protocol.getProtocol().length < 10) {
                throw new VerifyFailedException("数据长度不足");
            }
            return receiveVerifyData(protocol, i);
        }
        return null;
    }

    private IProtocol receiveVerifyData(IProtocol protocol, int len) throws VerifyFailedException {
        //先分解数据
        byte[] bytes = new byte[(len - 2) / 2 + 2];
        int index = 0;
        bytes[index++] = protocol.getProtocol()[0];
        for (int i = 1; i < len - 1; i += 2) {
            bytes[index++] = (byte) ((protocol.getProtocol()[i] - 0x30) * 16 + (protocol.getProtocol()[i + 1] - 0x30));
        }
        bytes[index++] = protocol.getProtocol()[len - 1];
        if (CrcVerify.crc8_Maxim(bytes, 1, bytes.length - 3) != bytes[bytes.length - 2]) {
            throw new VerifyFailedException("接受数据crc校验失败");
        }
        protocol.setProtocol(bytes);
        LogUtil.i(TAG, "解析完成的数据 " + StrUtil.byteToString(bytes));
        return protocol;
    }
}
