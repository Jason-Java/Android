package com.jason.system.manage.factory;

import com.jason.ApplicationContext;
import com.jason.system.model.domain.SysOperLog;
import com.jason.system.model.service.ISysOperLogService;
import com.jason.system.model.service.impl.SysOperLogServiceImpl;

import java.util.TimerTask;

public class AsyncFactory {


    private static ISysOperLogService operLogService = ApplicationContext.getBean(SysOperLogServiceImpl.class);


    public static TimerTask recordOperLog(final SysOperLog operLog) {
        return new TimerTask() {
            @Override
            public void run() {

                System.out.println("=====>保存数据库");
                operLogService.insertOperlog(operLog);
//                System.out.println(operLog.toString());
            }
        };
    }
}
