package com.jason.usb.command;

import android.graphics.Bitmap;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月15日
 */
public class MingYinPrintBitmapCommand extends MingYinAbsCommand {

    public MingYinPrintBitmapCommand(Bitmap bitmap) {
        byte[] bytes;
        int width = bitmap.getWidth();
        int heigh = bitmap.getHeight();
        int iDataLen = width * heigh;
        int[] pixels = new int[iDataLen];
        bitmap.getPixels(pixels, 0, width, 0, 0, width, heigh);
        bytes = PrintDiskImagefile(pixels, width, heigh);

        setCmd(bytes);
    }
    public static byte[] PrintDiskImagefile(int[] pixels, int iWidth, int iHeight) {
        int iBw = iWidth / 8;
        int iMod = iWidth % 8;
        if (iMod > 0)
            iBw = iBw + 1;
        int iDataLen = iBw * iHeight;
        byte[] bCmd = new byte[iDataLen + 8];
        int iIndex = 0;
        bCmd[iIndex++] = 0x1D;
        bCmd[iIndex++] = 0x76;
        bCmd[iIndex++] = 0x30;
        bCmd[iIndex++] = 0x0;
        bCmd[iIndex++] = (byte) iBw;
        bCmd[iIndex++] = (byte) (iBw >> 8);
        bCmd[iIndex++] = (byte) iHeight;
        bCmd[iIndex++] = (byte) (iHeight >> 8);

        int iValue1 = 0;
        int iValue2 = 0;
        int iRow = 0;
        int iCol = 0;
        int iW = 0;
        int iValue3 = 0;
        int iValue4 = 0;
        for (iRow = 0; iRow < iHeight; iRow++) {
            for (iCol = 0; iCol < iBw - 1; iCol++) {
                iValue2 = 0;

                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x80;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x40;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x20;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x10;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x8;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x4;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x2;
                iValue1 = pixels[iW++];
                if (iValue1 < -1)
                    iValue2 = iValue2 + 0x1;
                if (iValue3 < -1) // w1
                    iValue4 = iValue4 + 0x10;
                bCmd[iIndex++] = (byte) (iValue2);
            }
            iValue2 = 0;
            if (iValue4 > 0)      // w2
                iValue3 = 1;
            if (iMod == 0) {
                for (iCol = 8; iCol > iMod; iCol--) {
                    iValue1 = pixels[iW++];
                    if (iValue1 < -1)
                        iValue2 = iValue2 + (1 << iCol);
                }
            } else {
                for (iCol = 0; iCol < iMod; iCol++) {
                    iValue1 = pixels[iW++];
                    if (iValue1 < -1)
                        iValue2 = iValue2 + (1 << (8 - iCol));
                }
            }
            bCmd[iIndex++] = (byte) (iValue2);
        }
        return bCmd;
    }
}
