package com.jason.usb.command;

/**
 * <p>
 * 描述: 铭印打印机,设置字体打印方向指令
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinFontDirectionCommand extends MingYinAbsCommand {
    /**
     * 设置字体打印方向
     * @param iDirection 0从左到右 1旋转180度
     */
    public MingYinFontDirectionCommand(int iDirection) {
        byte[] bCmd = new byte[3];
        int iIndex = 0;
        bCmd[iIndex++] = 0x1B;
        bCmd[iIndex++] = 0x7B;
        if(iDirection != 1)
            bCmd[iIndex++] = 0;
        else{
            bCmd[iIndex++] = 1;
        }
            setCmd(bCmd);

    }
}
