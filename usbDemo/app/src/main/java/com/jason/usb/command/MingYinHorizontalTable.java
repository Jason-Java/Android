package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机命令, 横向制表符
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月02日
 */
public class MingYinHorizontalTable extends MingYinAbsCommand {
    /**
     * 横向制表符
     */
    public MingYinHorizontalTable() {
        byte[] bCmd = new byte[1];
        bCmd[0] = 0x09;
        setCmd(bCmd);
    }
}
