package com.jason.system.util;

/**
 * <p>描述:
 *
 *
 * <p>邮箱: fjz19971129@163.com
 *
 * @author 阿振
 * @version v1.0.0
 * @date：2022/10/16 13:05
 * @see
 */
public class ArrayUtil {


    public static boolean contain(Object[] array, Object objectToFind) {
        return indexOf(array, objectToFind, 0) >= 0;

    }




    public static int indexOf(Object[] array, Object objectToFind, int startIndex) {
        if (array == null) {
            return -1;
        } else {
            if (startIndex < 0) {
                startIndex = 0;
            }

            if (objectToFind == null) {
                for (int i = startIndex; i < array.length; i++) {
                    if (array[i] == null) {
                        return i;
                    }
                }
            } else {
                for (int i = startIndex; i < array.length; i++) {
                    if (objectToFind.equals(array[i])) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
}
