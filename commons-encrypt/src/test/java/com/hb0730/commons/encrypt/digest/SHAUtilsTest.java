package com.hb0730.commons.encrypt.digest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class SHAUtilsTest {
    private String content = "hello world";

    @Test
    public void test() {
        log.info(SHAUtils.sha(content));
        log.info(SHAUtils.sha1(content));
        log.info(SHAUtils.sha224(content));
        log.info(SHAUtils.sha256(content));
        log.info(SHAUtils.sha384(content));
        log.info(SHAUtils.sha512(content));
        log.info(SHAUtils.sha3_224(content));
        log.info(SHAUtils.sha3_256(content));
        log.info(SHAUtils.sha3_384(content));
        log.info(SHAUtils.sha3_512(content));
    }
}
