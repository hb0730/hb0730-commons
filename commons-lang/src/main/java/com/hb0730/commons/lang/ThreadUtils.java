package com.hb0730.commons.lang;

import java.util.concurrent.*;

/**
 * 线程相关工具类
 *
 * @author bing_huang
 * @date 2020/07/30 11:38
 * @since V1.0
 */
public class ThreadUtils {

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
