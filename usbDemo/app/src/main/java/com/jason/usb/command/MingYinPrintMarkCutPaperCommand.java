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
public class MingYinPrintMarkCutPaperCommand extends MingYinAbsCommand {
    public MingYinPrintMarkCutPaperCommand() {
        byte[] bCmd = new byte[2];
        int iIndex=0;
        bCmd[iIndex++]=0x1D;
        bCmd[iIndex++]=0x0C;
        setCmd(bCmd);
    }
}
