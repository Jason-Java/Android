package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,设置行间距指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinSetLineSpacingCommand extends MingYinAbsCommand {
    /**
     * @param lineSpacing 行间距 0-255
     */
    public MingYinSetLineSpacingCommand(int lineSpacing) {
        byte[] bCmd = new byte[3];
        int iIndex = 0;

        bCmd[iIndex++] = 0x1B;
        bCmd[iIndex++] =0x33;
        bCmd[iIndex++] = (byte)lineSpacing;
        setCmd(bCmd);
    }
}
