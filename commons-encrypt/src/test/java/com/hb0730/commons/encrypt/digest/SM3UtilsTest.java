package com.hb0730.commons.encrypt.digest;

import org.junit.Test;

public class SM3UtilsTest {
    private String content = "hello world";

    @Test
    public void test() {
        System.out.println(SM3Utils.sm3(content));
    }

}
