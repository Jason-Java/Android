package com.jason.usb.command;

/**
 * <p>
 * 描述:铭印打印机命令 设置黑标切纸偏移量
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月02日
 */
public class MingYinSetMarkOffSetCutCommand extends MingYinAbsCommand {
    /**
     * 铭印打印机,设置黑标切纸偏移量
     *
     * @param iOffset 偏移量
     */
    public MingYinSetMarkOffSetCutCommand(int iOffset) {
        byte[] bCmd = new byte[6];
        int iIndex = 0;
        bCmd[iIndex++]=0x13;
        bCmd[iIndex++]=0x74;
        bCmd[iIndex++]=0x33;
        bCmd[iIndex++]=0x78;
        if(iOffset > 1600){
            iOffset = 1600;
        }else{
            bCmd[iIndex++]=(byte) (iOffset>>8);
            bCmd[iIndex++]=(byte) iOffset;
        }
        setCmd(bCmd);
    }
}
