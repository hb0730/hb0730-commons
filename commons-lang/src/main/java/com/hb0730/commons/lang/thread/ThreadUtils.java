package com.hb0730.commons.lang.thread;

import java.util.concurrent.*;

/**
 * 线程相关工具类
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class ThreadUtils {

    /**
     * 新建一个线程池，默认的策略如下：
     * <ul>
     *     <li>
     *         1. 初始线程数为corePoolSize指定的大小
     *     </li>
     *     <li>
     *         2. 最大线程数为核心线程数的一倍，如果核心线程数为0则最大线程数为1
     *     </li>
     *     <li>
     *         3.默认使用LinkedBlockingQueue，默认队列大小为1024
     *     </li>
     *     <li>
     *         4.当运行线程大于corePoolSize放入队列，队列满后抛出异常
     *     </li>
     * </ul>
     *
     * @param corePoolSize 核心线程数
     * @return {@link ExecutorService}
     * @see ExecutorBuilder
     * @since 2.1.1
     */
    public static ExecutorService newExecutor(int corePoolSize) {
        ExecutorBuilder builder = ExecutorBuilder.create();
        if (corePoolSize > 0) {
            builder.setCorePoolSize(corePoolSize);
        }
        return builder.build();
    }

    /**
     * 获得一个新的线程池
     *
     * @param corePoolSize    初始线程池大小
     * @param maximumPoolSize 最大线程池大小
     * @return {@link ThreadPoolExecutor}
     * @see ExecutorBuilder
     * @since 2.1.1
     */
    public static ExecutorService newExecutor(int corePoolSize, int maximumPoolSize) {
        return ExecutorBuilder
                .create()
                .setCorePoolSize(corePoolSize)
                .setMaximumPoolSize(maximumPoolSize)
                .build();
    }

    /**
     * 获得一个新的线程池，并指定最大任务队列大小
     *
     * @param corePoolSize     初始线程池大小
     * @param maximumPoolSize  最大线程池大小
     * @param maximumQueueSize 最大任务队列大小
     * @return {@link ThreadPoolExecutor}
     * @see ExecutorBuilder
     * @since 2.1.1
     */
    public static ExecutorService newExecutor(int corePoolSize, int maximumPoolSize, int maximumQueueSize) {
        return ExecutorBuilder.create()
                .setCorePoolSize(corePoolSize)
                .setMaximumPoolSize(maximumPoolSize)
                .setWorkQueue(new LinkedBlockingQueue<>(maximumQueueSize))
                .build();
    }

    /**
     * 创建线程工厂
     *
     * @param prefix   线程名前缀
     * @param isDaemon 是否守护线程
     * @return {@link ThreadFactory}
     * @see NamedThreadFactory
     * @since 2.1.1
     */
    public static ThreadFactory newNamedThreadFactory(String prefix, boolean isDaemon) {
        return new NamedThreadFactory(prefix, isDaemon);
    }

    /**
     * 创建线程工厂
     *
     * @param prefix      线程名前缀
     * @param threadGroup 线程组，可以为null
     * @param isDaemon    是否守护线程
     * @return {@link ThreadFactory}
     * @see NamedThreadFactory
     * @since 2.1.1
     */
    public static ThreadFactory newNamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDaemon) {
        return new NamedThreadFactory(prefix, threadGroup, isDaemon);
    }

    /**
     * 创建线程工厂
     *
     * @param prefix      线程名前缀
     * @param threadGroup 线程组，可以为null
     * @param isDaemon    是否守护线程
     * @param handler     未捕获异常处理
     * @return {@link ThreadFactory}
     * @see NamedThreadFactory
     * @since 2.1.1
     */
    public static ThreadFactory newNamedThreadFactory(String prefix, ThreadGroup threadGroup, boolean isDaemon, Thread.UncaughtExceptionHandler handler) {
        return new NamedThreadFactory(prefix, threadGroup, isDaemon, handler);
    }

    /**
     * 创建新线程，非守护线程，正常优先级，线程组与当前线程的线程组一致
     *
     * @param runnable {@link Runnable}
     * @param name     线程名
     * @return {@link Thread}
     */
    public static Thread newThread(Runnable runnable, String name) {
        final Thread t = newThread(runnable, name, false);
        if (t.getPriority() != Thread.NORM_PRIORITY) {
            t.setPriority(Thread.NORM_PRIORITY);
        }
        return t;
    }

    /**
     * 创建新线程
     *
     * @param runnable {@link Runnable}
     * @param name     线程名
     * @param isDaemon 是否守护线程
     * @return {@link Thread}
     */
    public static Thread newThread(Runnable runnable, String name, boolean isDaemon) {
        final Thread t = new Thread(null, runnable, name);
        t.setDaemon(isDaemon);
        return t;
    }

    /**
     * sleep等待,单位为毫秒
     *
     * @param milliseconds 睡眠时长 毫秒级
     * @throws InterruptedException InterruptedException
     */
    public static void sleep(long milliseconds) throws InterruptedException {
        Thread.sleep(milliseconds);
    }

    /**
     * 停止线程池
     * <p>
     * 先使用shutdown, 停止接收新任务并尝试完成所有已存在任务.
     * 如果超时, 则调用shutdownNow, 取消在workQueue中Pending的任务,并中断所有阻塞函数.
     * 如果仍人超時，則強制退出.
     * 另对在shutdown时线程本身被调用中断做了处理.
     * </p>
     *
     * @param pool 线程池
     */
    public static void shutdownAndAwaitTermination(ExecutorService pool) {
        if (pool != null && !pool.isShutdown()) {
            pool.shutdown();
            try {
                if (!pool.awaitTermination(120, TimeUnit.SECONDS)) {
                    pool.shutdownNow();
                    pool.awaitTermination(120, TimeUnit.SECONDS);
                }
            } catch (InterruptedException ie) {
                pool.shutdownNow();
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * 打印线程异常信息
     *
     * @param r Runnable
     * @param t Throwable
     */
    public static void printException(Runnable r, Throwable t) {
        if (t == null && r instanceof Future<?>) {
            try {
                Future<?> future = (Future<?>) r;
                if (future.isDone()) {
                    future.get();
                }
            } catch (CancellationException ce) {
                t = ce;
            } catch (ExecutionException ee) {
                t = ee.getCause();
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
