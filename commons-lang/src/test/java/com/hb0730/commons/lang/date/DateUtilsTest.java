package com.hb0730.commons.lang.date;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Slf4j
public class DateUtilsTest {

    @Test
    public void nowTest() {
        Date now = DateUtils.now();
        Assert.assertNotNull(now);
        log.info(now.toString());
    }

    @Test
    public void addTest() {
        Date add = DateUtils.add(DateUtils.now(), 1, TimeUnit.DAYS);
        Assert.assertNotNull(add);
        log.info(add.toString());
    }

    @Test
    public void addYearsTest() {
        Date date = DateUtils.addYears(DateUtils.now(), 1);
        log.info(date.toString());
    }

    @Test
    public void addMonthsTest() {
        Date date = DateUtils.addMonths(DateUtils.now(), 1);
        log.info(date.toString());
    }

    @Test
    public void addWeeksTest() {
        Date date = DateUtils.addWeeks(DateUtils.now(), 1);
        log.info(date.toString());
    }

    @Test
    public void addDaysTest() {
        Date date = DateUtils.addDays(DateUtils.now(), 1);
        log.info(date.toString());
    }

    @Test
    public void addHoursTest() {
        Date date = DateUtils.addHours(DateUtils.now(), 1);
        log.info(date.toString());
    }

    @Test
    public void addMinutesTest() {
        Date date = DateUtils.addMinutes(DateUtils.now(), 30);
        log.info(date.toString());
    }

    @Test
    public void addSecondsTest() {
        Date date = DateUtils.addSeconds(DateUtils.now(), 1800);
        log.info(date.toString());
    }

    @Test
    public void addMillisecondsTest() {
        Date date = DateUtils.addMilliseconds(DateUtils.now(), 30 * 60 * 1000);
        log.info(date.toString());
    }

    @Test
    public void betweenTest() {
        long between = DateUtils.between(DateUtils.now(), DateUtils.addDays(DateUtils.now(), 1), DateMsUnit.DAY);
        Assert.assertNotNull(between);
        log.info(String.valueOf(between));
    }

    @Test
    public void testBetweenTest() {
        long between = DateUtils.between(DateUtils.now(), DateUtils.addDays(DateUtils.now(), 1), DateMsUnit.DAY, false);
        Assert.assertNotNull(between);
        log.info(String.valueOf(between));
        between = DateUtils.between(DateUtils.addDays(DateUtils.now(), 1), DateUtils.now(), DateMsUnit.DAY, true);
        Assert.assertNotNull(between);
        log.info(String.valueOf(between));
    }

    @Test
    public void getServerStartDateTest() {
        Date date = DateUtils.getServerStartDate();
        Assert.assertNotNull(date);
        log.info(date.toString());
    }

    @Test
    public void convertToTest() {
        Calendar calendar = DateUtils.convertTo(DateUtils.now());
        Assert.assertNotNull(calendar);
    }
}
