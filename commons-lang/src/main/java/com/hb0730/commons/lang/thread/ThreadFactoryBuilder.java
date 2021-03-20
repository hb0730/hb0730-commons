package com.hb0730.commons.lang.thread;

import com.hb0730.commons.lang.builder.Builder;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * {@link ThreadFactory}构造器
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class ThreadFactoryBuilder implements Builder<ThreadFactory> {
    /**
     * 线程名前缀
     */
    private String namePrefix;
    /**
     * 是否守护线程，默认false
     */
    private Boolean isDaemon;
    /**
     * 线程优先级
     */
    private Integer priority;
    /**
     * 未捕获异常处理器
     */
    private Thread.UncaughtExceptionHandler handler;
    /**
     * 用于线程创建的线程工厂类
     */
    private ThreadFactory threadFactory;

    /**
     * 设置线程名前缀
     *
     * @param namePrefix 线程名前缀
     * @return this
     */
    public ThreadFactoryBuilder setNamePrefix(String namePrefix) {
        this.namePrefix = namePrefix;
        return this;
    }

    /**
     * 设置是否守护线程
     *
     * @param daemon 是否守护线程
     * @return this
     */
    public ThreadFactoryBuilder isDaemon(boolean daemon) {
        this.isDaemon = daemon;
        return this;
    }

    /**
     * 设置线程优先级
     *
     * @param priority 优先级
     * @return this
     * @see Thread#MIN_PRIORITY
     * @see Thread#NORM_PRIORITY
     * @see Thread#MAX_PRIORITY
     * @see Thread#setPriority(int)
     */
    public ThreadFactoryBuilder setPriority(int priority) {
        if (priority > Thread.MAX_PRIORITY || priority < Thread.MIN_PRIORITY) {
            throw new IllegalArgumentException(String.format(" If the priority is not in the range %s to %s", Thread.MIN_PRIORITY, Thread.MAX_PRIORITY));
        }
        this.priority = priority;
        return this;
    }

    /**
     * 设置未捕获异常的处理方式
     *
     * @param uncaughtExceptionHandler {@link Thread.UncaughtExceptionHandler}
     * @return this
     */
    public ThreadFactoryBuilder setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        this.handler = uncaughtExceptionHandler;
        return this;
    }

    /**
     * 设置用于创建基础线程的线程工厂
     *
     * @param backingThreadFactory 用于创建基础线程的线程工厂
     * @return this
     */
    public ThreadFactoryBuilder setThreadFactory(ThreadFactory backingThreadFactory) {
        this.threadFactory = backingThreadFactory;
        return this;
    }


    /**
     * 创建{@link ThreadFactoryBuilder}
     *
     * @return {@link ThreadFactoryBuilder}
     */
    public static ThreadFactoryBuilder create() {
        return new ThreadFactoryBuilder();
    }

    @Override
    public ThreadFactory build() {
        return build(this);
    }

    /**
     * 构建
     *
     * @param builder {@link ThreadFactoryBuilder}
     * @return {@link ThreadFactory}
     */
    private static ThreadFactory build(ThreadFactoryBuilder builder) {
        final String namePrefix = builder.namePrefix;
        final ThreadFactory threadFactory = (null != builder.threadFactory) ? builder.threadFactory : Executors.defaultThreadFactory();
        final AtomicInteger poolNumber = (null == namePrefix) ? null : new AtomicInteger(1);
        final Boolean isDaemon = builder.isDaemon;
        final Integer priority = builder.priority;
        final Thread.UncaughtExceptionHandler handler = builder.handler;
        return (r) -> {
            Thread thread = threadFactory.newThread(r);
            if (null != namePrefix) {
                thread.setName(namePrefix + poolNumber.getAndIncrement());
            }
            if (null != isDaemon) {
                thread.setDaemon(isDaemon);
            }
            if (null != priority) {
                thread.setPriority(priority);
            }
            if (null != handler) {
                thread.setUncaughtExceptionHandler(handler);
            }
            return thread;
        };
    }
}
