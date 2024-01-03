package com.jason.usb.command;

import com.lotsrc.zuilib.security.core.AbstractCoder;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月02日
 */
public class MingYinFeedLineCommand extends MingYinAbsCommand {
    /**
     * 走纸命令
     * @param line 走纸行数
     */
    public MingYinFeedLineCommand(int line) {
        byte[] bCmd = new byte[3];
        int iIndex = 0;
        bCmd[iIndex++]= 0x1B;
        bCmd[iIndex++]= 0x64;
        bCmd[iIndex++]= (byte) line;
        setCmd(bCmd);
    }
}
