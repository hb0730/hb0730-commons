package com.hb0730.commons.encrypt.digest;

import org.junit.Test;

public class SHAUtilsTest {
    private String content = "hello world";

    @Test
    public void test() {
        System.out.println(SHAUtils.sha(content));
        System.out.println(SHAUtils.sha1(content));
        System.out.println(SHAUtils.sha224(content));
        System.out.println(SHAUtils.sha256(content));
        System.out.println(SHAUtils.sha384(content));
        System.out.println(SHAUtils.sha512(content));
        System.out.println(SHAUtils.sha3_224(content));
        System.out.println(SHAUtils.sha3_256(content));
        System.out.println(SHAUtils.sha3_384(content));
        System.out.println(SHAUtils.sha3_512(content));
    }
}
