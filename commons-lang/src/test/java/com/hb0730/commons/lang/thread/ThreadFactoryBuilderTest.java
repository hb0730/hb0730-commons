package com.hb0730.commons.lang.thread;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadFactoryBuilderTest {

    @Test
    public void createTest() {
        ThreadFactory factory = ThreadFactoryBuilder
                .create()
                .setThreadFactory(Executors.defaultThreadFactory())
                .setNamePrefix("test-pool")
                .setPriority(2)
                .isDaemon(false)
                .build();
        Assert.assertNotNull(factory);
    }

    @Test
    public void createTest2() {
        ThreadFactory factory = ThreadFactoryBuilder
                .create()
                .setThreadFactory(Executors.defaultThreadFactory())
                .setNamePrefix("test-pool")
                .setPriority(0)
                .isDaemon(false)
                .build();
        Assert.assertNotNull(factory);
    }

    @Test
    public void createTest3() {
        ThreadFactory factory = ThreadFactoryBuilder
                .create()
                .setThreadFactory(Executors.defaultThreadFactory())
                .setNamePrefix("test-pool")
                .setPriority(11)
                .isDaemon(false)
                .build();
        Assert.assertNotNull(factory);
    }
}
