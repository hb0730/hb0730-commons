package com.hb0730.commons.lang.thread;

import com.hb0730.commons.lang.builder.Builder;

import java.util.concurrent.*;

/**
 * {@link ThreadPoolExecutor}构造者
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class ExecutorBuilder implements Builder<ThreadPoolExecutor> {
    private static final long serialVersionUID = -1542018033631957924L;
    private static final int DEFAULT_QUEUE_CAPACITY = 1024;
    /**
     * 核心线程数
     */
    private int corePoolSize;
    /**
     * 最大线程数
     */
    private int maximumPoolSize;
    /**
     * 当线程数大于核心线程数时，多余的空闲线程存活的最长时间
     */
    private long keepAliveTime = 0;

    /**
     * 时间单位
     */
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;
    /**
     * 任务队列，用来储存等待执行任务的队列
     */
    private BlockingQueue<Runnable> workQueue;
    /**
     * 线程工厂，用来创建线程
     */
    private ThreadFactory threadFactory;
    /**
     * 拒绝策略
     */
    private RejectedExecutionHandler handler;
    /**
     * 线程执行超时后是否回收线程
     */
    private Boolean allowCoreThreadTimeOut;

    /**
     * 设置核心线程数
     *
     * @param corePoolSize 核心线程数
     * @return this
     * @see ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue, ThreadFactory, RejectedExecutionHandler)
     */
    public ExecutorBuilder setCorePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    /**
     * 设置最大线程数
     *
     * @param maximumPoolSize 最大线程数
     * @return this
     * @see ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue, ThreadFactory, RejectedExecutionHandler)
     */
    public ExecutorBuilder setMaximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    /**
     * 设置空闲线程存活的最长时间，默认为0
     *
     * @param keepAliveTime 空闲线程存活的最长时间
     * @return this
     */
    public ExecutorBuilder setKeepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }

    /**
     * 设置时间单位,默认为{@link TimeUnit#MILLISECONDS}
     *
     * @param timeUnit 时间单位
     * @return this
     */
    public ExecutorBuilder setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    /**
     * 设置任务队列,用于存在未执行的线程<br>
     * 可选队列有：
     * <ul>
     *     <li>
     *         1. {@link SynchronousQueue}    它将任务直接提交给线程而不保持它们。当运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
     *     </li>
     *     <li>
     *         2. {@link LinkedBlockingQueue} 默认无界队列，当运行线程大于corePoolSize时始终放入此队列，此时maxPoolSize无效。
     * 	                        当构造LinkedBlockingQueue对象时传入参数，变为有界队列，队列满时，运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
     *     </li>
     *     <li>
     *         3. {@link ArrayBlockingQueue}  有界队列，相对无界队列有利于控制队列大小，队列满时，运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
     *     </li>
     * </ul>
     *
     * @param workQueue 任务队列
     * @return this
     * @see ThreadPoolExecutor#ThreadPoolExecutor(int, int, long, TimeUnit, BlockingQueue, ThreadFactory, RejectedExecutionHandler)
     */
    public ExecutorBuilder setWorkQueue(BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        return this;
    }

    /**
     * 设置工作队列，使用{@link SynchronousQueue}<br>
     * 它将任务直接提交给线程而不保持它们。当运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
     *
     * @return this
     * @see SynchronousQueue#SynchronousQueue()
     */
    public ExecutorBuilder useSynchronousQueue() {
        return useSynchronousQueue(false);
    }

    /**
     * 设置工作队列，使用{@link SynchronousQueue}<br>
     * 它将任务直接提交给线程而不保持它们。当运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
     *
     * @param fair 是否采用公平访问策略
     * @return this
     * @see SynchronousQueue#SynchronousQueue(boolean)
     */
    public ExecutorBuilder useSynchronousQueue(boolean fair) {
        return setWorkQueue(new SynchronousQueue<>(fair));
    }

    /**
     * 设置工作队列,使用{@link ArrayBlockingQueue}<br>
     * 有界队列，相对无界队列有利于控制队列大小，队列满时，运行线程小于maxPoolSize时会创建新线程，否则触发异常策略
     *
     * @param capacity 队列容量
     * @return this
     * @see ArrayBlockingQueue#ArrayBlockingQueue(int)
     */
    public ExecutorBuilder useArrayBlockingQueue(int capacity) {
        return setWorkQueue(new ArrayBlockingQueue<>(capacity));
    }

    /**
     * 设置线程工厂
     *
     * @param threadFactory 线程工厂
     * @return this
     */
    public ExecutorBuilder setThreadFactory(ThreadFactory threadFactory) {
        this.threadFactory = threadFactory;
        return this;
    }

    /**
     * 设置当线程阻塞（block）时的异常处理器，所谓线程阻塞即线程池和等待队列已满，无法处理线程时采取的策略
     * <p>
     * 此处可以使用JDK预定义的几种策略，见{@link RejectPolicy}枚举
     *
     * @param handler {@link RejectedExecutionHandler}
     * @return this
     * @see RejectPolicy
     */
    public ExecutorBuilder setHandler(RejectedExecutionHandler handler) {
        this.handler = handler;
        return this;
    }

    /**
     * 设置当线程阻塞（block）时的异常处理器，所谓线程阻塞即线程池和等待队列已满，无法处理线程时采取的策略
     * <p>
     * 此处可以使用JDK预定义的几种策略，见{@link RejectPolicy}枚举
     *
     * @param handler {@link RejectPolicy}
     * @return this
     */
    public ExecutorBuilder setHandler(RejectPolicy handler) {
        return setHandler(handler.getValue());
    }

    /**
     * 设置线程执行超时后是否回收线程
     *
     * @param allowCoreThreadTimeOut 线程执行超时后是否回收线程
     * @return this
     */
    public ExecutorBuilder setAllowCoreThreadTimeOut(boolean allowCoreThreadTimeOut) {
        this.allowCoreThreadTimeOut = allowCoreThreadTimeOut;
        return this;
    }

    /**
     * 创建ExecutorBuilder，开始构建
     *
     * @return {@link ExecutorBuilder}
     */
    public static ExecutorBuilder create() {
        return new ExecutorBuilder();
    }

    @Override
    public ThreadPoolExecutor build() {
        return build(this);
    }

    /**
     * 构建{@link ThreadPoolExecutor}
     *
     * @param builder {@link ExecutorBuilder}
     * @return {@link ThreadPoolExecutor}
     */
    private static ThreadPoolExecutor build(ExecutorBuilder builder) {
        final int corePoolSize = builder.corePoolSize;
        final int maximumPoolSize = builder.maximumPoolSize;
        final long keepAliveTime = builder.keepAliveTime;
        final TimeUnit timeUnit = builder.timeUnit;
        final BlockingQueue<Runnable> workQueue;
        if (null != builder.workQueue) {
            workQueue = builder.workQueue;
        } else {
            // corePoolSize为0则要使用SynchronousQueue避免无限阻塞
            workQueue = (corePoolSize <= 0) ? new SynchronousQueue<>() : new LinkedBlockingQueue<>(DEFAULT_QUEUE_CAPACITY);
        }
        final ThreadFactory threadFactory = (null != builder.threadFactory) ? builder.threadFactory : Executors.defaultThreadFactory();
        final RejectedExecutionHandler handler = (null != builder.handler) ? builder.handler : new ThreadPoolExecutor.AbortPolicy();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue, threadFactory, handler);
        if (null != builder.allowCoreThreadTimeOut) {
            threadPoolExecutor.allowCoreThreadTimeOut(builder.allowCoreThreadTimeOut);
        }
        return threadPoolExecutor;
    }
}
