package com.hb0730.commons.lang.thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ThreadUtilsTest {
    @Test
    public void newThreadTest() {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, r -> ThreadUtils.newThread(r, String.format("cache-prune-timer-%d", 1)));
        Assert.assertNotNull(poolExecutor);
    }

    @Test
    public void testNewThreadTest() {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, r -> ThreadUtils.newThread(r, String.format("cache-prune-timer-%d", 1), false));
        Assert.assertNotNull(poolExecutor);
    }

    @Test
    public void sleepTest() {
        try {
            System.out.println("hhhhhh--------");
            ThreadUtils.sleep(2 * 1000);
            System.out.println("test---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Assert.assertNotNull(e);
        }
    }

    @Test
    public void shutdownAndAwaitTerminationTest() {
        ScheduledThreadPoolExecutor poolExecutor = new ScheduledThreadPoolExecutor(1, r -> ThreadUtils.newThread(r, String.format("cache-prune-timer-%d", 1)));
        Assert.assertNotNull(poolExecutor);
        ThreadUtils.shutdownAndAwaitTermination(poolExecutor);
    }
}
