package com.hb0730.commons.lang.date;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class DateUtilsTest {

    @Test
    public void nowTest() {
        System.out.println(DateUtils.now());
    }

    @Test
    public void addTest() {
        System.out.println(DateUtils.add(DateUtils.now(), 1, TimeUnit.DAYS));
    }

    @Test
    public void addYearsTest() {
        System.out.println(DateUtils.addYears(DateUtils.now(), 1));
    }

    @Test
    public void addMonthsTest() {
        System.out.println(DateUtils.addMonths(DateUtils.now(), 1));
    }

    @Test
    public void addWeeksTest() {
        System.out.println(DateUtils.addWeeks(DateUtils.now(), 1));
    }

    @Test
    public void addDaysTest() {
        System.out.println(DateUtils.addDays(DateUtils.now(), 1));
    }

    @Test
    public void addHoursTest() {
        System.out.println(DateUtils.addHours(DateUtils.now(), 1));
    }

    @Test
    public void addMinutesTest() {
        System.out.println(DateUtils.addMinutes(DateUtils.now(), 30));
    }

    @Test
    public void addSecondsTest() {
        System.out.println(DateUtils.addSeconds(DateUtils.now(), 1800));
    }

    @Test
    public void addMillisecondsTest() {
        System.out.println(DateUtils.addMilliseconds(DateUtils.now(), 30 * 60 * 1000));
    }
}
