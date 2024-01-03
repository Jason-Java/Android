package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,后退纸指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinBackPaperCommand extends MingYinAbsCommand {
    /**
     * 后退纸
     *
     * @param n 退纸的垂直点距,一个点距为0.125mm
     *          0<=n<=255
     */
    public MingYinBackPaperCommand(int n) {
        byte[] bCmd = new byte[3];
        int iIndex = 0;

        bCmd[iIndex++] = 0x1B;
        bCmd[iIndex++] = 0x4B;
        bCmd[iIndex++] = (byte) n;
        setCmd(bCmd);
    }
}
