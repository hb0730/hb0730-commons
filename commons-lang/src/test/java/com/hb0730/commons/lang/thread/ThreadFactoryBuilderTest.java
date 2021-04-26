package com.hb0730.commons.lang.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

@Slf4j
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
        try {
            ThreadFactory factory = ThreadFactoryBuilder
                    .create()
                    .setThreadFactory(Executors.defaultThreadFactory())
                    .setNamePrefix("test-pool")
                    .setPriority(0)
                    .isDaemon(false)
                    .build();
            Assert.assertNotNull(factory);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

    }

    @Test
    public void createTest3() {
        try {
            ThreadFactory factory = ThreadFactoryBuilder
                    .create()
                    .setThreadFactory(Executors.defaultThreadFactory())
                    .setNamePrefix("test-pool")
                    .setPriority(11)
                    .isDaemon(false)
                    .build();
            Assert.assertNotNull(factory);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
