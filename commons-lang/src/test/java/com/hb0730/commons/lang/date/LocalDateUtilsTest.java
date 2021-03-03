package com.hb0730.commons.lang.date;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

public class LocalDateUtilsTest {

    @Test
    public void nowTest() {
        LocalDate date = LocalDateUtils.now();
        Assert.assertNotNull(date);
    }

    @Test
    public void testNowTest() {
        LocalDate date = LocalDateUtils.now(ZoneId.systemDefault());
        Assert.assertNotNull(date);
        date = LocalDateUtils.now(TimeZone.getTimeZone("GMT-8").toZoneId());
        Assert.assertNotNull(date);
        date = LocalDateUtils.now(TimeZone.getTimeZone("America/Los_Angeles").toZoneId());
        Assert.assertNotNull(date);
        date = LocalDateUtils.now((ZoneId) null);
        Assert.assertNotNull(date);
    }

    @Test
    public void testNow1Test() {
        LocalDate date = LocalDateUtils.now("GMT+8");
        Assert.assertNotNull(date);
        date = LocalDateUtils.now("GMT-8");
        Assert.assertNotNull(date);
        date = LocalDateUtils.now("America/Los_Angeles");
        Assert.assertNotNull(date);
        date = LocalDateUtils.now((String) null);
        Assert.assertNotNull(date);
        date = LocalDateUtils.now("");
        Assert.assertNotNull(date);
    }

    @Test
    public void ofTest() {
        LocalDate date = LocalDateUtils.of(2021, 3, 3);
        Assert.assertNotNull(date);
    }
}
