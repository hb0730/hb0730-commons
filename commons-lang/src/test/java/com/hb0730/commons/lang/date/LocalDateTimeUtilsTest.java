package com.hb0730.commons.lang.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
        Assert.assertNull("参数为空", dateTime);
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
        Assert.assertNull("参数为空", dateTime);
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
        Assert.assertNull("参数为空", dateTime);
    }

    @Test
    public void parseTest() {
        //2011-12-03T10:15:30
        String date = "2020-08-17T08:16:59";
        LocalDateTime dateTime = LocalDateTimeUtils.parse(date);
        Assert.assertNotNull(dateTime);
        date = "2020-08-17 08:16:59";
        dateTime = LocalDateTimeUtils.parse(date, "yyyy-MM-dd HH:mm:ss");
        Assert.assertNotNull(dateTime);
        dateTime = LocalDateTimeUtils.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Assert.assertNotNull(dateTime);
        try {
            dateTime = LocalDateTimeUtils.parse(date, (String) null);
            Assert.assertNotNull(dateTime);
            dateTime = LocalDateTimeUtils.parse(date, (DateTimeFormatter) null);
            Assert.assertNotNull(dateTime);
            dateTime = LocalDateTimeUtils.parse(null, (String) null);
            Assert.assertNotNull(date);
            dateTime = LocalDateTimeUtils.parse((CharSequence) null);
            Assert.assertNotNull(dateTime);
        } catch (Exception e) {
            log.error("参数为空");
        }

    }

    @Test
    public void parseDateTest() {
        String date = "2020-08-17";
        LocalDate localDate = LocalDateTimeUtils.parseDate(date);
        Assert.assertNotNull(localDate);
        date = "2020/08/17";
        localDate = LocalDateTimeUtils.parseDate(date, "yyyy/MM/dd");
        Assert.assertNotNull(localDate);
        localDate = LocalDateTimeUtils.parseDate(date, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        Assert.assertNotNull(localDate);
        try {
            localDate = LocalDateTimeUtils.parseDate(date, (String) null);
            Assert.assertNotNull(localDate);
            localDate = LocalDateTimeUtils.parseDate(null, (String) null);
            Assert.assertNotNull(localDate);
            localDate = LocalDateTimeUtils.parseDate(null);
            Assert.assertNotNull(localDate);
        } catch (Exception e) {
            log.error("参数为空");
        }
    }

    @Test
    public void offsetTest() {
        LocalDateTime dateTime = LocalDateTimeUtils.now();
        LocalDateTime offset = LocalDateTimeUtils.offset(dateTime, 1, ChronoUnit.DAYS);
        Duration between = LocalDateTimeUtils.between(dateTime, offset);
        Assert.assertNotNull(between);

    }

    @Test
    public void betweenTest() {
        LocalDateTime dateTime = LocalDateTimeUtils.now();
        LocalDateTime offset = LocalDateTimeUtils.offset(dateTime, 1, ChronoUnit.DAYS);
        Duration between = LocalDateTimeUtils.between(dateTime, offset);
        Assert.assertNotNull(between);

    }

    @Test
    public void beginOfDayTest() {
        LocalDateTime dateTime = LocalDateTimeUtils.beginOfDay(LocalDateTimeUtils.now());
        Assert.assertNotNull(dateTime);
    }

    @Test
    public void endOfDayTest() {
        LocalDateTime dateTime = LocalDateTimeUtils.endOfDay(LocalDateTimeUtils.now());
        Assert.assertNotNull(dateTime);
    }
}
