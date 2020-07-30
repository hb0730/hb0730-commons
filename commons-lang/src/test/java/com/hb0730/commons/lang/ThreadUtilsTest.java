package com.hb0730.commons.lang;

import org.junit.Test;

public class ThreadUtilsTest {

    @Test
    public void testSleep() {
        try {
            System.out.println("hhhhhh--------");
            ThreadUtils.sleep(500000);
            System.out.println("test---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testShutdownAndAwaitTermination() {
    }

    @Test
    public void testPrintException() {
    }
}
