package com.hb0730.commons.lang.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.TimeZone;

@Slf4j
public class LocalDateTimeUtilsTest {

    @Test
    public void nowTest() {
        LocalDateTime now = LocalDateTimeUtils.now();
        Assert.assertNotNull(now);
    }

    @Test
    public void ofTest() {
        Instant instant = Instant.now();
        LocalDateTime dateTime = LocalDateTimeUtils.of(instant, ZoneId.systemDefault());
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of(instant, "Asia/Shanghai");
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of(instant);
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of((Instant) null);
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void testOfTest() {
        Instant instant = Instant.now();
        LocalDateTime dateTime = LocalDateTimeUtils.of(instant, TimeZone.getDefault());
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of(instant, "Asia/Shanghai");
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of(instant);
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of((Instant) null);
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void testOf1Test() {
        long epochMilli = Instant.now().toEpochMilli();
        LocalDateTime dateTime = LocalDateTimeUtils.of(epochMilli, TimeZone.getDefault());
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of(epochMilli, "Asia/Shanghai");
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of(epochMilli);
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void testOf2Test() {
        LocalDateTime dateTime = LocalDateTimeUtils.of(new Date());
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.of((Date) null);
        Assert.assertNotNull(dateTime);
    }
}
