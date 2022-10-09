package com.jason.system.manage;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * 异步任务
 */
public class AsyncManager {


    @Resource(name = "scheduledExecutorService")
    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor;

    private AsyncManager() {
    }

    private static  class SingleAsyncManager{
        private static AsyncManager asyncManager = new AsyncManager();
    }

    public static AsyncManager me() {
        return SingleAsyncManager.asyncManager;
    }

    public void execute(TimerTask task) {
        scheduledThreadPoolExecutor.execute();
    }


}
