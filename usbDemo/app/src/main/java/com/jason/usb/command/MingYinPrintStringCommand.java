package com.jason.usb.command;

import java.io.UnsupportedEncodingException;

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
public class MingYinPrintStringCommand extends MingYinAbsCommand {
    /**
     * 铭印打印机,打印字符串指令
     *
     * @param str        需要打印的字符串
     * @param isLineFeed 是否换行 ture 换行 false 不换行
     */
    public MingYinPrintStringCommand(String str, boolean isLineFeed) {
        // 字符串转换为byte[]数组
        byte[] strAarry;
        try {
            strAarry = str.getBytes("GB2312");
            int iLen = strAarry.length;
            if (isLineFeed)
                iLen = iLen + 1;
            byte[] bCmd = new byte[iLen];
            System.arraycopy(strAarry, 0, bCmd, 0, strAarry.length);
            if (isLineFeed)
                bCmd[iLen - 1] = 0x0A;
            setCmd(bCmd);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
