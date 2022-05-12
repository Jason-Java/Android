package util;


import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    //手机号正则表达式
    private static final String IS_PHONE = "^[1](([3|5|8][\\d])|([4][4,5,6,7,8,9])|([6][2,5,6,7])|([7][^9])|([9][1,8,9]))[\\d]{8}$";
    //邮箱正则表达式
    private static final String IS_EMAIL = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    //密码正则表达式
    private static final String IS_PASSWORD = "^(?![0-9]+$)(?![^0-9]+$)(?![a-zA-Z]+$)(?![^a-zA-Z]+$)(?![a-zA-Z0-9]+$)[a-zA-Z0-9\\S]{8,}$";
    //提取数字
    private static final String EXTRACTION_OF_DIGITAL = "[^0-9||\\.]";
    //提取字母
    private static final String EXTRACTION_OF_LETTER = "[^a-z||A-Z]";

    private static final String CHECK_NUMBER = "[\\+-]?[0-9]*(\\.[0-9]*)?([eE][\\+-]?[0-9]+)?";


    public StringUtil() {

    }

    /**
     * 判断字符串是否纯数字
     *
     * @param str
     * @return
     */
    public static boolean isDigit(String str) {
        if (str == null) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) < 48 || str.charAt(i) > 57) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断是否是手机号
     *
     * @param str
     * @return
     */
    public static boolean isPhone(String str) {
        if (str == null) {
            return false;
        }
        if (str.matches(IS_PHONE) == true) {
            return true;
        }
        return false;
    }

    /**
     * 是否是电子邮箱
     *
     * @param str
     * @return {@code true} 如果字符串包含@符号
     */
    public static boolean isEmail(String str) {
        if (str == null) {
            return false;
        }
        if (str.matches(IS_EMAIL) == true) {
            return true;
        }
        return false;
    }

    /**
     * 判断密码格式是否符合八位以上，且以字母开头
     *
     * @param str
     * @return
     */
    public static boolean isPassWord(String str) {
        if (str == null) {
            return false;
        }
        return str.matches(IS_PASSWORD);
    }


    /**
     * 判断字符串是否为空
     * StringUtil.isEmpty("")   =true
     * StringUtil.isEmpty("    ")   =true
     * StringUtil.isEmpty("asb  ")   =false
     * StringUtil.isEmpty("  abd  ")   =false
     * StringUtil.isEmpty("abc")   =false
     *
     * @param str
     * @return
     */
    public static <T> boolean isEmpty(T str) {
        if (str == null) return true;

        if (str instanceof String) {
            int length;
            String value = (String) str;
            if ((length = value.length()) == 0) return true;

            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(value.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * 移除字符串串两端的空格
     *
     * @param str 如果字符串为空 则返回空，否者返回移除掉空格的字符串
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 从字符串中提取数字
     */
    public static String extractionOfDigital(@NonNull String str) {
        Pattern p = Pattern.compile(EXTRACTION_OF_DIGITAL);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 从字符串中提取字母
     */
    public static String extractionOfLetter(@NonNull String str) {
        Pattern p = Pattern.compile(EXTRACTION_OF_LETTER);
        Matcher m = p.matcher(str);
        return m.replaceAll("").trim();
    }

    /**
     * 判断是否是数值
     *
     * @param str
     * @return
     */
    public static boolean isDigital(@NonNull String str) {
        Pattern p = Pattern.compile(CHECK_NUMBER);
        Matcher m = p.matcher(str);
        return m.matches();
    }

    /**
     * 比较两个字符串是否相等
     * StringUtil.equals(null,null)   =false
     * StringUtil.equals("    ","    ")   =true
     * StringUtil.equals("asb  ","asb")   =false
     * StringUtil.equals("","")   =true
     */
    public static boolean equals(String source, String destination) {
        if (source == null || destination == null) {
            return false;
        }
        if (source.equals(destination)) {
            return true;
        }
        return false;
    }


    /**
     * 获取Modbus CRC校验码
     *
     * @param bytes
     * @return
     */
    private static String getCRCModbus(byte[] bytes) {
        int CRC = 0x0000ffff;
        int POLYNOMIAL = 0x0000a001;
        int i, j;
        for (i = 0; i < bytes.length; i++) {
            CRC ^= ((int) bytes[i] & 0x000000ff);
            for (j = 0; j < 8; j++) {
                if ((CRC & 0x00000001) != 0) {
                    CRC >>= 1;
                    CRC ^= POLYNOMIAL;
                } else {
                    CRC >>= 1;
                }
            }
        }
        CRC = ((CRC & 0x0000FF00) >> 8) | ((CRC & 0x000000FF) << 8);
        return Integer.toHexString(CRC);
    }

    /**
     * Hex字符串转成byte数组
     *
     * @param str hex字符串
     * @return byte 数组
     */
    private static byte[] hexStringToBytes(String str) {
//        if (str.length() % 2 != 0) {
//            LogUtil.i("CRC转换失败 长度为奇数");
//            return new byte[]{00, 00, 00};
//        }
        int size = str.length() / 2;
        byte[] bytes = new byte[size];
        for (int i = 0; i < size; ++i) {
            bytes[i] = (byte) Integer.parseInt(str.substring(i * 2, (i * 2 + 2)), 16);
        }
        return bytes;
    }

    /**
     * 获取字符串类型的Modbus CRC校验码
     *
     * @param data byte 数据类型
     * @return crc
     */
    public final static String getCRCModbusString(byte[] data) {
        return getCRCModbus(data);
    }

    /**
     * 获取 Byte 类型的Modbus CRC校验码
     *
     * @param data byte 数据类型
     * @return crc
     */
    public final static byte[] getCRCModbusByte(byte[] data) {
        return hexStringToBytes(getCRCModbus(data));
    }


    /**
     * hex字符串转ASCLL码字符串
     *
     * @param r hex字符串
     * @return Ascll码字符串
     */
    public final static String hexToString(String r) {
        StringBuffer bufff = new StringBuffer();
        for (int i = 0; i < r.length(); i += 2) {
            String s = r.substring(i, i + 2);
            int decimal = Integer.parseInt(s, 16);
            bufff.append((char) decimal);
        }
        return bufff.toString();
    }

    /**
     * hex数组转换ASCLL码字符串
     *
     * @param data byte数组
     * @return ASCLL码字符串
     */
    public final static String hexToString(byte[] data) {
        StringBuffer bufff = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            bufff.append((char) data[i]);
        }
        return bufff.toString();
    }

    /**
     * 字节数组转float
     * 采用IEEE 754标准
     *
     * @param bytes
     * @return
     */
    public final static float bytes2Float(byte[] bytes) {
        //获取 字节数组转化成的2进制字符串
        String BinaryStr = bytes2BinaryStr(bytes);
        //符号位S
        Long s = Long.parseLong(BinaryStr.substring(0, 1));
        //指数位E
        Long e = Long.parseLong(BinaryStr.substring(1, 9), 2);
        //位数M
        String M = BinaryStr.substring(9);
        float m = 0, a, b;
        for (int i = 0; i < M.length(); i++) {
            a = Integer.valueOf(M.charAt(i) + "");
            b = (float) Math.pow(2, i + 1);
            m = m + (a / b);
        }
        Float f = (float) ((Math.pow(-1, s)) * (1 + m) * (Math.pow(2, (e - 127))));
        return f;
    }

    /**
     * 将字节数组转换成2进制字符串
     *
     * @param bytes byte数组
     * @return 二进制字符串
     */
    public final static String bytes2BinaryStr(byte[] bytes) {
        StringBuffer binaryStr = new StringBuffer();
        for (int i = 0; i < bytes.length; i++) {
            String str = Integer.toBinaryString((bytes[i] & 0xFF) + 0x100).substring(1);
            binaryStr.append(str);
        }
        return binaryStr.toString();
    }

    /**
     * 数值进行四舍五入,小数值必须大于等于一位
     *
     * @param value  数值
     * @param length 小数的长度
     */
    public static String decimalRound(String value, int length) {
        if (length > 10) {
            length = 10;
        }
        double dataDouble = 0;
        //先扩大10^length+1次方
        long dataInteger = (long) (Double.parseDouble(value) * Math.pow(10, length + 1));
        //取出最后一位标志位 判断是否大于等于5
        int flag = (int) dataInteger % 10;
        dataInteger = dataInteger / 10;
        if (flag >= 5) {
            dataInteger += 1;
        } else if (flag <= -5) {
            dataInteger -= 1;
        }
        //恢复小数
        dataDouble = dataInteger / Math.pow(10, length);

        //检查小数位数
        String dataString = String.valueOf(dataDouble);
        String integerPart = dataString.split("\\.")[0];
        String decimalPart = dataString.split("\\.")[1];
        int decimalLength = decimalPart.length();
        if (decimalLength >= length) {
            decimalPart = decimalPart.substring(0, length);
        } else {
            for (int i = 0; i < length - decimalLength; i++) {
                decimalPart += "0";
            }
        }
        return integerPart + "." + decimalPart;
    }
    /**
     * 转换特殊时间格式
     * 如 2022-05-11T15:23:13.8474074+08:00
     */
    public static String timeFormat(String value) {
        if(StringUtil.isEmpty(value))
            return null;
        if(!value.contains("T"))
            return value;
        return value.substring(0, 19).replace('T', ' ');
    }

}
