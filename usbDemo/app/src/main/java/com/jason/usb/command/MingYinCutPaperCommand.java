package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机命令, 切纸
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinCutPaperCommand extends MingYinAbsCommand {
    /**
     * 切纸
     */
    public MingYinCutPaperCommand() {
        byte[] bCmd = new byte[3];
        int iIndex = 0;

        bCmd[iIndex++] = 0x1B;
        bCmd[iIndex++] = 0x69;
        bCmd[iIndex++] = 0x0;
        setCmd(bCmd);
    }
}
