package com.jason.config;

import com.jason.system.util.Threads;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class ThreadPoolConfig {

    //核心线程数
    private final static int corePoolSize = 50;
    //最大线程数
    private final static int maxPoolSize = 200;
    //空闲时间
    private final static int keepAliveSeconds = 300;
    //等待队列长度
    private final static int queueCapacity = 1000;


    @Bean(name = "threadPoolTaskExecutor")
    public ThreadPoolTaskExecutor getThreadPool() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setKeepAliveSeconds(keepAliveSeconds);
        executor.setQueueCapacity(queueCapacity);

        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }

    /**
     * 执行周期性或定时任务
     *
     * @return
     */
    @Bean(name = "scheduledExecutorService")
    public ScheduledThreadPoolExecutor getThreadPoolTaskScheduler() {
        return new ScheduledThreadPoolExecutor(corePoolSize, new MyThreadFactory()
                , new ThreadPoolExecutor.CallerRunsPolicy()) {
            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                super.afterExecute(r, t);
                Threads.printException(r, t);
            }
        };
    }

    /**
     * 线程工厂
     */
    private class MyThreadFactory implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setDaemon(true);
            thread.setName(String.format("scheduler-thread-%d", getThreadPoolTaskScheduler().getTaskCount()));
            return thread;
        }
    }
}
