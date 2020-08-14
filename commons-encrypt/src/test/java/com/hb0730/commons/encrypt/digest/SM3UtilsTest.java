package com.hb0730.commons.encrypt.digest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SM3UtilsTest {
    private String content = "hello world";

    @Test
    public void test() {
        log.info(SM3Utils.sm3(content));
    }

}
