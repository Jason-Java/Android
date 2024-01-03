package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,检测黑标位置指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月31日
 */
public class MingYinTestMarkPositionCommand extends MingYinAbsCommand {
    public MingYinTestMarkPositionCommand() {
        byte[] cmd = new byte[4];
        int iIndex = 0;
        cmd[iIndex++] = 0x1D;
        cmd[iIndex++] = 0x56;
        cmd[iIndex++] = 0x42;
        cmd[iIndex++] = 0x0;
        setCmd(cmd);
    }
}
