package com.jason.jasontools.commandbus;


import com.jason.jasontools.util.LogUtil;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 命令执行中心
 */
public class CommandExecuteCenter implements Runnable {
    private static final String TAG = "CommandExecuteCenter";
    /**
     * 队列的最大容量
     */
    private static final int QueueCapacity = 100;

    /**
     * 队列
     */
    private volatile LinkedList<AbsCommand> queue;

    /**
     * 线程运行标志
     */
    private volatile boolean isSend = false;

    /**
     * 线程锁
     */
    private final Lock lock = new ReentrantLock(true);
    private Condition condition = lock.newCondition();


    public CommandExecuteCenter() {
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        queue = new LinkedList<>();
    }

    /**
     * 向发送中心队列添加指令消息
     * 若队列已满,则移除队列第一个元素,再添加
     * 根据命令的优先级,将指令添加到队列中合适的位置{@link AbsCommand#getIndex()}
     *
     * @param command
     */
    public void addQueue(AbsCommand command) {
        lock.lock();
        try {
            if (queue.size() >= QueueCapacity) {
                queue.remove(0);
            }
            int i = 0;
            for (; i < queue.size(); i++) {
                if (command.getIndex() > queue.get(i).getIndex()) {
                    queue.add(i + 1, command);
                    break;
                }
            }
            if (i == queue.size()) {
                queue.add(command);
            }
        } catch (Exception ignore) {
            LogUtil.e(TAG,"通风模组队列已满,添加线程已阻塞");
        } finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {
        LogUtil.i(TAG,"发送命令线程启动成功-------");
        isSend = true;
        try {
            while (!Thread.currentThread().isInterrupted() && isSend) {
                try {
                    sendMessage();
                } catch (InterruptedException ignore) {
                    break;
                }
            }
        } finally {
            isSend = false;
            Thread.currentThread().interrupt();
        }
        LogUtil.i(TAG,"发送命令线程结束-------");
    }

    //发送消息
    private void sendMessage() throws InterruptedException {
        lock.lock();
        try {
            // 如果队列为空
            while (queue.size() == 0) {
                LogUtil.i(TAG,"队列为空,线程阻塞");
                condition.await(1, TimeUnit.SECONDS);
            }
            final AbsCommand command = queue.get(0);
            command.execute(new RepeaterListener() {
                @Override
                public void onRepeatCommand() {
                    //重发次命令
                    command.execute(this);
                }

                @Override
                public void onNext() {
                    lock.lock();
                    try {
                        queue.remove(0);
                        //唤醒线程
                        condition.signalAll();
                    } finally {
                        lock.unlock();
                    }
                }
            });
            //线程休眠
            condition.await();
        } catch (Exception e) {
            LogUtil.e(TAG,"发送命令线程异常: " + e.getMessage());
            lock.lock();
            try {
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }

    //停止线程
    public void stop() {
        isSend = false;
        Thread.currentThread().interrupt();
    }

}
