package com.jason.system.manage.factory;

import com.jason.system.model.domain.SysOperLog;
import com.jason.system.util.ip.IpUtils;

import java.util.TimerTask;

public class AsyncFactory {


    public static TimerTask recordOper(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {
                System.out.println("=====>保存数据库");
                System.out.println(operLog.toString());
            }
        };
    }
}
