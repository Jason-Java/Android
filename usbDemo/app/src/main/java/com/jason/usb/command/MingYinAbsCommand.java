package com.jason.usb.command;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年07月31日
 */
public abstract class MingYinAbsCommand {
    private byte[] cmd;

    public void setCmd(byte[] cmd) {
        this.cmd = cmd;
    }

    public byte[] getCmd() {
        return cmd;
    }
}
