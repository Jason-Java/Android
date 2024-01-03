package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机命令, 打印并走纸 如果没有打印内容, 只走纸
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinPrintAndFeedCommand extends MingYinAbsCommand{
    /**
     * 打印并走纸
     * 没有打印内容, 只走纸一行纸
     */
    public MingYinPrintAndFeedCommand() {
        byte[] bCmd = new byte[2];
        int iIndex = 0;
        bCmd[iIndex++] = 0x0A;
        setCmd(bCmd);
    }
}
