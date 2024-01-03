package com.jason.usb.command;

/**
 * <p>
 * 描述:铭印打印机,退出中文模式指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月31日
 */
public class MingYinExitChineseModel extends MingYinAbsCommand{
    public MingYinExitChineseModel() {
        byte[] cmd = new byte[2];
        cmd[0] = 0x1C;
        cmd[1] = 0x2E;
        setCmd(cmd);
    }
}
