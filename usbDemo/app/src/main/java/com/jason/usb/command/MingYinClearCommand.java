package com.jason.usb.command;

/**
 * <p>
 * 描述:铭印打印机,清空缓存指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月31日
 */
public class MingYinClearCommand extends MingYinAbsCommand{
    public MingYinClearCommand() {
        byte[] cmd = new byte[2];
        cmd[0] = 0x1B;
        cmd[1] = 0x49;
        setCmd(cmd);
    }
}
