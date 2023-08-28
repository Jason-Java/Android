package com.jason.jasontools.util;


/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年08月22日
 */
public class CrcVerify {
    private static final String TAG = "CrcVerifyTAG";

    public static byte crc8_Maxim(byte[] bytes, int offset, int len){
        int wCRCin = 0x00;
        int wCPoly = 0x8C;
        for (int i = offset; i < offset+len; i++) {
            wCRCin ^= ((long) bytes[i] & 0xFF);
            for (int j = 0; j < 8; j++) {
                if ((wCRCin & 0x01) != 0) {
                    wCRCin >>= 1;
                    wCRCin ^= wCPoly;
                } else {
                    wCRCin >>= 1;
                }
            }
        }
        wCRCin ^= 0x00;
        LogUtil.e(TAG, "crc = " + StrUtil.byteToString((byte) wCRCin));
        return (byte) wCRCin;
    }


}
