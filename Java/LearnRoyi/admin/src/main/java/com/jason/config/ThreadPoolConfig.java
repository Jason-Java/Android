package com.jason.config;

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
    private static int corePoolSize = 50;
    //最大线程数
    private static int maxPoolSize = 200;
    //空闲时间
    private static  int keepAliveSeconds=300;
    //等待队列长度
    private static  int queueCapacity=1000;


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


    public ScheduledThreadPoolExecutor getThreadPoolTaskScheduler() {
      return new ScheduledThreadPoolExecutor(corePoolSize, new ThreadFactory() {
          @Override
          public Thread newThread(Runnable r) {
              Thread thread = new Thread();
              thread.setName(String.format("scheduler-thread-%s",r.getClass()));
              thread.setDaemon(true);

              return thread;
          }
      });
    }








}
