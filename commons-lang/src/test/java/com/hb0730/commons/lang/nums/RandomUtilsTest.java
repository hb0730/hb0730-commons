package com.hb0730.commons.lang.nums;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class RandomUtilsTest {

    @Test
    public void randomNumberTest() {
        String s = RandomUtils.randomNumber(5);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void randomStringTest() {
        String s = RandomUtils.randomString(5, false);
        Assert.assertNotNull(s);
        log.info(s);

        s = RandomUtils.randomString(5, true);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void randomStringWithSpecialCharTest() {
        String s = RandomUtils.randomStringWithSpecialChar(5);
        Assert.assertNotNull(s);
        log.info(s);
    }

    @Test
    public void uuidTest() {
        String uuid = RandomUtils.uuid();
        Assert.assertNotNull(uuid);
        log.info(uuid);
    }

    @Test
    public void randomIntTest() {
        int i = RandomUtils.randomInt();
        Assert.assertNotNull(i);
        log.info(String.valueOf(i));
    }

    @Test
    public void testRandomIntTest() {
        int i = RandomUtils.randomInt(1, 999);
        Assert.assertNotNull(i);
        log.info(String.valueOf(i));
    }

    @Test
    public void randomBytesTest() {
        byte[] bytes = RandomUtils.randomBytes(8);
        Assert.assertEquals("长度不相等", bytes.length, 8);
    }
}
