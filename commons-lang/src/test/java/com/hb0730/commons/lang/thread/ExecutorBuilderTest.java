package com.hb0730.commons.lang.thread;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorBuilderTest {
    private ExecutorBuilder builder = null;

    @Before
    public void init() {
        builder = ExecutorBuilder.create();
    }

    @Test
    public void createTest() {
        builder.setCorePoolSize(1).setMaximumPoolSize(1);
        ThreadPoolExecutor executor = builder.build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .useSynchronousQueue(true)
                .build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .useSynchronousQueue()
                .build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .useArrayBlockingQueue(10)
                .build();
        Assert.assertNotNull(executor);


        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .useArrayBlockingQueue(10)
                .setThreadFactory(Executors.defaultThreadFactory())
                .build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .useArrayBlockingQueue(10)
                .setThreadFactory(Executors.defaultThreadFactory())
                .setHandler(RejectPolicy.ABORT).build();
        Assert.assertNotNull(executor);

        executor = builder
                .setCorePoolSize(1)
                .setMaximumPoolSize(1)
                .setKeepAliveTime(100)
                .setTimeUnit(TimeUnit.MILLISECONDS)
                .useArrayBlockingQueue(10)
                .setThreadFactory(Executors.defaultThreadFactory())
                .setHandler(RejectPolicy.ABORT)
                .setAllowCoreThreadTimeOut(true)
                .build();
        Assert.assertNotNull(executor);

    }
}
