package com.hb0730.commons.encrypt.digest;

import com.hb0730.commons.encrypt.constant.HmacType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class HmacUtilsTest {
    private String content = "hello world";

    @Test
    public void generateKey() {
        log.info(HmacUtils.generateKeyHex(HmacType.HmacMD5));
        log.info(HmacUtils.generateKeyHex(HmacType.HmacSHA1));
        log.info(HmacUtils.generateKeyHex(HmacType.HmacSHA224));
        log.info(HmacUtils.generateKeyHex(HmacType.HmacSHA256));
        log.info(HmacUtils.generateKeyHex(HmacType.HmacSHA384));
        log.info(HmacUtils.generateKeyHex(HmacType.HmacSHA512));

        System.out.println();

        log.info(HmacUtils.generateHmacMD5KeyHex());
        log.info(HmacUtils.generateHmacSHA1KeyHex());
        log.info(HmacUtils.generateHmacSHA224KeyHex());
        log.info(HmacUtils.generateHmacSHA256KeyHex());
        log.info(HmacUtils.generateHmacSHA384KeyHex());
        log.info(HmacUtils.generateHmacSHA512KeyHex());
    }

    @Test
    public void test() {
        String keyHex = HmacUtils.generateHmacMD5KeyHex();
        log.info(HmacUtils.hmacMD5(content, keyHex));
        log.info(HmacUtils.hmacSHA1(content, HmacUtils.generateHmacSHA1KeyHex()));
        log.info(HmacUtils.hmacSHA224(content, HmacUtils.generateHmacSHA224KeyHex()));
        log.info(HmacUtils.hmacSHA256(content, HmacUtils.generateHmacSHA256KeyHex()));
        log.info(HmacUtils.hmacSHA384(content, HmacUtils.generateHmacSHA384KeyHex()));
        log.info(HmacUtils.hmacSHA512(content, HmacUtils.generateHmacSHA512KeyHex()));
    }


}
