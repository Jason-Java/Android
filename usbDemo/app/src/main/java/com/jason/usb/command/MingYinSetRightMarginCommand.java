package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,设置右边距指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月31日
 */
public class MingYinSetRightMarginCommand extends MingYinAbsCommand{
    public MingYinSetRightMarginCommand(int margin) {
        byte[] cmd = new byte[3];
        cmd[0] = 0x1B;
        cmd[1] = 0x51;
        cmd[2] = (byte) margin;
        setCmd(cmd);
    }
}
