package com.hb0730.commons.lang.date;

import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class CalendarUtilsTest {

    @Test
    public void calenderTest() {
        Calendar calender = CalendarUtils.calendar();
        Assert.assertNotNull(calender);
    }

    @Test
    public void calendarTest() {
        Date now = DateUtils.now();
        Calendar calendar = CalendarUtils.calendar(now.getTime());
        Assert.assertNotNull(calendar);
    }

    @Test
    public void testCalendarTest() {
        Date now = DateUtils.now();
        Calendar calendar = CalendarUtils.calendar(now);
        Assert.assertNotNull(calendar);
    }

    @Test
    public void toLocalDateTimeTest() {
        Date now = DateUtils.now();
        Calendar calendar = CalendarUtils.calendar(now);
        LocalDateTime localDateTime = CalendarUtils.toLocalDateTime(calendar);
        Assert.assertNotNull(localDateTime);
    }

    @Test
    public void isSameDayTest() throws ParseException {
        Date now = DateUtils.now();
        Calendar calendar = CalendarUtils.calendar(now);
        boolean sameDay = CalendarUtils.isSameDay(calendar, calendar);
        Assert.assertTrue(sameDay);
        String date = "2020/08/16";
        Date date1 = DateUtils.toDate(date, "yyyy/MM/dd");
        Calendar calendar1 = CalendarUtils.calendar(date1);
        sameDay = CalendarUtils.isSameDay(calendar, calendar1);
        Assert.assertFalse("是相同一天", sameDay);
    }

    @Test
    public void toInstantTest() {
        Calendar calender = CalendarUtils.calendar();
        Instant instant = CalendarUtils.toInstant(calender);
        Assert.assertNotNull(instant);
        instant = CalendarUtils.toInstant(null);
        Assert.assertNull("参数为空", instant);
    }
}
