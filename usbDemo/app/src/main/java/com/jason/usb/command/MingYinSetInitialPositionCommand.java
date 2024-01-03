package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机命令,设置初始位置
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinSetInitialPositionCommand extends MingYinAbsCommand {
    /**
     * 初始化打印位置
     */
    public MingYinSetInitialPositionCommand(int x, int y) {
        byte[] bCmd = {0x11, 0x24, 0x0, 0x0, 0x0, 0x0};
        bCmd[2] = (byte) (x/256);
        bCmd[3] = (byte) (x%256);
        bCmd[4] = (byte) (y/256);
        bCmd[5] = (byte) (y%256);
        setCmd(bCmd);
    }
}
