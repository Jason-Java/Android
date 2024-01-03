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
public class MingYinFontBoldCommand extends MingYinAbsCommand {
    /**
     * 字体加粗
     */
    public MingYinFontBoldCommand(boolean isBold) {
        byte[] bCmd = new byte[3];
        int iIndex = 0;
        bCmd[iIndex++] = 0x1B;
        bCmd[iIndex++] = 0x47;
        if (isBold) {
            bCmd[iIndex++] = 0x01;
        } else {
            bCmd[iIndex++] = 0x00;
        }
        setCmd(bCmd);
    }
}
