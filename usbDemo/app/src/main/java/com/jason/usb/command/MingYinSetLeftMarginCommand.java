package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,设置左边距指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinSetLeftMarginCommand extends MingYinAbsCommand{
    public MingYinSetLeftMarginCommand(int margin) {
        byte[] bCmd = new byte[4];
        int iIndex = 0;
        bCmd[iIndex++] = 0x1D;
        bCmd[iIndex++] = 0x4C;
        if (margin> 576) {
            bCmd[iIndex++] = 0;
            bCmd[iIndex++] = 0;
        } else {
            bCmd[iIndex++] = (byte) (margin% 256);
            bCmd[iIndex++] = (byte) (margin/ 256);
        }
        setCmd(bCmd);
    }
}
