package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,设置页面模式
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月31日
 */
@Deprecated
public class MingYinSetPageModelCommand extends MingYinAbsCommand {
    public MingYinSetPageModelCommand() {
        byte[] cmd = new byte[2];
        int iIndex = 0;
        cmd[iIndex++] = 0x11;
        cmd[iIndex++] = 0x21;
        setCmd(cmd);
    }
}
