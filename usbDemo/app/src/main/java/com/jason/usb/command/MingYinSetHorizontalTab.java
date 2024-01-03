package com.jason.usb.command;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月02日
 */
public class MingYinSetHorizontalTab extends MingYinAbsCommand{

    public MingYinSetHorizontalTab(byte[] bHTseat, int iLength) {
        byte[] bCmd = new byte[35];
        int iIndex = 0;
        int x, length;
        if (iLength > 32)
            length = 32;
        else
            length = iLength;

        bCmd[iIndex++] = 0x1B;
        bCmd[iIndex++] = 0x44;
        for (x = 0; x < length; x++) {
            bCmd[iIndex++] = bHTseat[x];
        }
        bCmd[iIndex++] = 0x00;
    }
}
