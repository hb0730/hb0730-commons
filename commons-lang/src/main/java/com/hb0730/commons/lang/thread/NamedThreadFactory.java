package com.hb0730.commons.lang.thread;

import com.hb0730.commons.lang.StringUtils;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程创建工厂类，此工厂可选配置：
 *
 * <pre>
 * 1. 自定义线程命名前缀
 * 2. 自定义是否守护线程
 * </pre>
 *
 * @author bing_huang
 * @see Executors#defaultThreadFactory()
 * @since 2.1.1
 */
public class NamedThreadFactory implements ThreadFactory {
    /**
     * 命名前缀
     */
    private final String prefix;
    /**
     * 线程组
     */
    private final ThreadGroup group;
    /**
     * 线程组
     */
    private final AtomicInteger threadNumber = new AtomicInteger(1);
    /**
     * 是否守护线程
     */
    private final boolean isDaemon;
    /**
     * 无法捕获的异常统一处理
     */
    private final Thread.UncaughtExceptionHandler handler;


    /**
     * 构造
     *
     * @param prefix   线程名前缀
     * @param isDaemon 是否守护线程
     */
    public NamedThreadFactory(String prefix, boolean isDaemon) {
        this(prefix, null, isDaemon);
    }

    /**
     * 构造
     *
     * @param prefix      线程名前缀
     * @param threadGroup 线程组，可以为null
     * @param isDaemon    是否守护线程
     */
    public NamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDaemon) {
        this(prefix, threadGroup, isDaemon, null);
    }

    /**
     * 构造
     *
     * @param prefix      线程名前缀
     * @param threadGroup 线程组，可以为null
     * @param isDaemon    是否守护线程
     * @param handler     未捕获异常处理
     */
    public NamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDaemon, Thread.UncaughtExceptionHandler handler) {
        this.prefix = StringUtils.isBlank(prefix) ? "thread-pool" : prefix;
        this.group = (null != threadGroup) ? threadGroup : Thread.currentThread().getThreadGroup();
        this.isDaemon = isDaemon;
        this.handler = handler;
    }

    @Override
    public Thread newThread(Runnable r) {
        final Thread thread = new Thread(this.group, r, String.format("%s%s", prefix, threadNumber.getAndIncrement()));
        //守护线程
        if (!thread.isDaemon()) {
            if (isDaemon) {
                // 原线程为非守护则设置为守护
                thread.setDaemon(true);
            }
        } else if (!isDaemon) {
            // 原线程为守护则还原为非守护
            thread.setDaemon(false);
        }
        //异常处理
        if (null != this.handler) {
            thread.setUncaughtExceptionHandler(handler);
        }
        //优先级
        if (Thread.NORM_PRIORITY != thread.getPriority()) {
            // 标准优先级
            thread.setPriority(Thread.NORM_PRIORITY);
        }
        return thread;
    }
}
