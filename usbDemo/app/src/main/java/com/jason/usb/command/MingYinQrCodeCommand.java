package com.jason.usb.command;


import com.jason.usb.msprintsdk.JNAData1;

import java.io.UnsupportedEncodingException;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月01日
 */
public class MingYinQrCodeCommand extends MingYinAbsCommand {
    public MingYinQrCodeCommand(int mSize, String strData) {
        byte[] strArray;
        try {
            strArray = strData.getBytes("GB2312");
            byte[] bCmd = new byte[25 + strArray.length];
            int iIndex = 0;
            bCmd[iIndex++] = 0x13;
            bCmd[iIndex++] = 0x50;
            bCmd[iIndex++] = 0x48;
            bCmd[iIndex++] = 0x1;
            bCmd[iIndex++] = 0x1;

            bCmd[iIndex++] = 0x13;
            bCmd[iIndex++] = 0x50;
            bCmd[iIndex++] = 0x48;
            bCmd[iIndex++] = 0x2;
            bCmd[iIndex++] = 0x1;

            bCmd[iIndex++] = 0x13;
            bCmd[iIndex++] = 0x50;
            bCmd[iIndex++] = 0x48;
            bCmd[iIndex++] = 0x3;
            if (mSize < 1 || mSize > 9)   // 1-9
                mSize = 5;
            else
                bCmd[iIndex++] = (byte) mSize;

            bCmd[iIndex++] = 0x13;
            bCmd[iIndex++] = 0x50;
            bCmd[iIndex++] = 0x48;
            bCmd[iIndex++] = 0x4;

            for (int i = 0; i < strArray.length; i++) {
                bCmd[iIndex++] = strArray[i];
            }
            bCmd[iIndex++] = 0x0;
            bCmd[iIndex++] = 0x13;
            bCmd[iIndex++] = 0x50;
            bCmd[iIndex++] = 0x48;
            bCmd[iIndex++] = 0x5;
            setCmd(bCmd);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 打印二维码
     * @param strData   打印数据
     * @param iLmargin 左边距
     * @param iMside QrCode大小 1-8
     * @param iRound 环绕模式，1 环绕（混排，有些机型不支持）、0立即打印（不混排）
     */
    public MingYinQrCodeCommand(String strData,int iLmargin,int iMside,int iRound){
        try
        {
            int iResult = JNAData1.INSTANCE.Data1PrintQrcode(strData,iLmargin,iMside,iRound);
            if(iResult > 0) {
                String strPrintData = JNAData1.INSTANCE.Data1GetPrintDataA();
                byte[] bData =JNAStringToByte(strPrintData,iResult);
                JNAData1.INSTANCE.Data1Release();
                setCmd(bData);
            }

        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static byte[] JNAStringToByte(String strData,int iLen)
    {
        int iIndex = 0;
        byte[] bData1 = new byte[iLen];
        int iValue1 = 0;
        String strValue1 = "";

        for (iIndex = 0; iIndex < iLen; iIndex++)
        {
            strValue1 = strData.substring(iIndex*2,iIndex*2+1);
            iValue1 = Integer.valueOf(strValue1,16);
            iValue1 = iValue1 * 16;

            strValue1 = strData.substring(iIndex*2+1,iIndex*2+2);
            iValue1 = iValue1 + Integer.valueOf(strValue1,16);
            bData1[iIndex] = (byte)iValue1;
        }

        return bData1;
    }

}
