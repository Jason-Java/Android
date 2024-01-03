package com.jason.hisensedemo;

import com.topband.chyuanfingersdk.FingerPrintManager;
import com.topband.libserial.core.Logger;
import com.topband.libserial.core.SerialConfig;

/**
 * <p>
 * 描述:
 * </P>
 *
 * @author 阿振
 * @version 1.0
 * @email fjz19971129@163.com
 * @createTime 2023年11月03日
 */
public class FingerController {
    public static void init() {
        FingerPrintManager.init(new SerialConfig() {
            @Override
            public String getSerialPath() {
                return "/dev/ttyS1";
            }

            @Override
            public int getRate() {
                return 9600;
            }

            @Override
            public boolean printLog() {
                return false;
            }
        }, new Logger() {
            @Override
            public void d(String s, String s1) {

            }

            @Override
            public void w(String s, String s1) {

            }

            @Override
            public void e(String s, String s1) {

            }
        });
        FingerPrintManager.get().getDeviceAddress();
    }
}
