package com.hb0730.commons.lang.thread;

import org.junit.Test;

public class ThreadUtilsTest {

    @Test
    public void testSleepTest() {
        try {
            System.out.println("hhhhhh--------");
            ThreadUtils.sleep(500000);
            System.out.println("test---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShutdownAndAwaitTerminationTest() {
    }

    @Test
    public void testPrintExceptionTest() {
    }
}
