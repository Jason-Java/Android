package com.jason.usb.command;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinTestCommand extends MingYinAbsCommand {
    /**
     *
     */
    public MingYinTestCommand() {
        byte[] bCmd = new byte[4];
        int iIndex = 0;

        bCmd[iIndex++] = 0x1D;
        bCmd[iIndex++] = 0x57;
        bCmd[iIndex++] = (byte)250;
        bCmd[iIndex++] = (byte)250;
        setCmd(bCmd);
    }
}
