package com.jason.system.manage;

import com.jason.ApplicationContext;
import com.jason.system.util.Threads;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.Resource;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务
 */
public class AsyncManager {

    //操作延迟时间
    private static final int OPERATE_DELAY_TIME = 1000*2;


    private ScheduledThreadPoolExecutor scheduledThreadPoolExecutor= ApplicationContext.getBean("scheduledExecutorService");

    private AsyncManager() {
    }

    private static class SingleAsyncManager {
        private static AsyncManager asyncManager = new AsyncManager();
    }

    public static AsyncManager me() {
        return SingleAsyncManager.asyncManager;
    }

    /**
     * 执行任务
     *
     * @param task
     */
    public void schedule(TimerTask task) {

        scheduledThreadPoolExecutor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }

    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(scheduledThreadPoolExecutor);
    }


}
