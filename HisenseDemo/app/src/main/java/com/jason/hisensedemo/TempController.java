package com.jason.hisensedemo;

import com.topband.libserial.core.Logger;
import com.topband.libserial.core.SerialConfig;
import com.topband.libserial.model.IData;
import com.topband.ultralowtemperaturesdk.HxUltraTemperatureManager;
import com.topband.ultralowtemperaturesdk.interf.IResultListener;

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
public class TempController {
    public static void init() {
        HxUltraTemperatureManager.init(new SerialConfig() {
            @Override
            public String getSerialPath() {
                return "/dev/ttyS4";
            }

            @Override
            public int getRate() {
                return 9600;
            }

            @Override
            public boolean printLog() {
                return true;
            }
        },
                new Logger() {
            @Override
            public void d(String s, String s1) {
                System.out.println("jason d  "+ s+"  "+s1);
            }

            @Override
            public void w(String s, String s1) {

                System.out.println("jason w "+s+"  "+s1);
            }

            @Override
            public void e(String s, String s1) {

                System.out.println("jason e"+s+"  "+s1);
            }
        });
        HxUltraTemperatureManager.get().serResultListener(new IResultListener() {
            @Override
            public void onReceiverResultChange(IData iData) {

            }
        });
        HxUltraTemperatureManager.get().queryCabinetTemperature();
    }
}
