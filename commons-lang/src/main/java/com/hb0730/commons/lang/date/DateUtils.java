package com.hb0730.commons.lang.date;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.Validate;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

/**
 * Date utils
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class DateUtils {
    public static final String FORMAT_DEFAULT_DATE = "yyyy-MM-dd";
    public static final String FORMAT_DEFAULT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_ZONE = "GMT+8";

    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return new Date();
    }

    /**
     * 根据{@link #FORMAT_DEFAULT_DATE}格式化日期
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     * @see #formatDate(Date, String)
     * @since 1.0.2
     */
    public static String formatDate(Date date) {
        return formatDate(date, FORMAT_DEFAULT_DATE);
    }

    /**
     * 根据{@link #FORMAT_DEFAULT_DATETIME}格式化日期
     *
     * @param date 被格式化的日期
     * @return 格式化后的日期
     * @see #formatDate(Date, String)
     * @since 1.0.2
     */
    public static String formatDateTime(Date date) {
        return formatDate(date, FORMAT_DEFAULT_DATETIME);
    }

    /**
     * 时间格式化，默认时区{@link #TIME_ZONE}
     *
     * @param date   日期
     * @param format 格式化
     * @return 字符串类型的时间
     * @since 1.0.2
     */
    public static String formatDate(Date date, String format) {
        return formatDate(date, format, TimeZone.getTimeZone(TIME_ZONE));
    }

    /**
     * 时间格式化
     *
     * @param date     日期
     * @param format   格式化
     * @param timeZone 时区 {@link TimeZone}
     * @return 字符串类型的时间
     * @since 1.0.2
     */
    public static String formatDate(Date date, String format, TimeZone timeZone) {
        if (null == date) {
            return null;
        }
        final SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.format(date);
    }

    /**
     * 构建LocalDateTime对象
     *
     * @param date   时间字符串（带格式）
     * @param format 格式
     * @return LocalDateTime对象
     * @see LocalDateTimeUtils#parse(CharSequence, String)
     * @since 1.0.2
     */
    public static LocalDateTime parseLocalDateTime(CharSequence date, String format) {
        return LocalDateTimeUtils.parse(date, format);
    }

    /**
     * 字符串转日期，默认时区{@link TimeZone},默认格式为{@link #FORMAT_DEFAULT_DATE}
     *
     * @param date 被转的日期字符串类型
     * @return 日期
     * @throws ParseException 转换失败
     * @since 1.0.2
     */
    public static Date toDate(String date) throws ParseException {
        return toDate(date, FORMAT_DEFAULT_DATE);
    }

    /**
     * 字符串转日期，默认时区{@link TimeZone},默认格式为{@link #FORMAT_DEFAULT_DATETIME}
     *
     * @param date 被转的日期字符串类型
     * @return 日期
     * @throws ParseException 转换失败
     * @since 1.0.2
     */
    public static Date toDateTime(String date) throws ParseException {
        return toDate(date, FORMAT_DEFAULT_DATETIME);
    }


    /**
     * 字符串转日期，默认时区{@link TimeZone}
     *
     * @param date   被转的日期字符串类型
     * @param format 格式化
     * @return 日期
     * @throws ParseException 转换失败
     * @since 1.0.2
     */
    public static Date toDate(String date, String format) throws ParseException {
        return toDate(date, format, TimeZone.getTimeZone(TIME_ZONE));
    }

    /**
     * 字符串转日期
     *
     * @param date     被转的日期字符串类型
     * @param format   格式化
     * @param timeZone 时区 {@link TimeZone}
     * @return 日期
     * @throws ParseException 转换失败
     * @since 1.0.2
     */
    public static Date toDate(String date, String format, TimeZone timeZone) throws ParseException {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        dateFormat.setTimeZone(timeZone);
        return dateFormat.parse(date);
    }

    /**
     * Date对象转换为{@link Instant}对象
     *
     * @param date Date对象
     * @return {@link Instant}对象
     * @since 1.0.2
     */
    public static Instant toInstant(Date date) {
        return null == date ? null : date.toInstant();
    }

    /**
     * Date对象转换为{@link Instant}对象
     *
     * @param temporalAccessor Date对象
     * @return {@link Instant}对象
     * @since 1.0.2
     */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        return TemporalAccessorUtils.toInstant(temporalAccessor);
    }

    /**
     * {@link Instant} 转换为 {@link LocalDateTime}，使用系统默认时区
     *
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     * @see LocalDateTimeUtils#of(Instant)
     * @since 1.0.2
     */
    public static LocalDateTime toLocalDateTime(Instant instant) {
        return LocalDateTimeUtils.of(instant);
    }


    /**
     * {@link Date} 转换为 {@link LocalDateTime}，使用系统默认时区
     *
     * @param date {@link Date}
     * @return {@link LocalDateTime}
     * @see LocalDateTimeUtils#of(Date)
     * @since 1.0.2
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTimeUtils.of(date);
    }

    /**
     * 时间追加
     *
     * @param date     时间 不为null
     * @param time     时间 不能小于1
     * @param timeUnit 时间类型 不为空
     * @return 时间完后时间
     */
    public static Date add(Date date, long time, TimeUnit timeUnit) {
        if (null == date || time < 0 || timeUnit == null) {
            return null;
        }
        Date result;

        int timeIntValue;

        if (time > Integer.MAX_VALUE) {
            timeIntValue = Integer.MAX_VALUE;
        } else {
            timeIntValue = Long.valueOf(time).intValue();
        }

        // Calc the expiry time
        switch (timeUnit) {
            case DAYS:
                result = addDays(date, timeIntValue);
                break;
            case HOURS:
                result = addHours(date, timeIntValue);
                break;
            case MINUTES:
                result = addMinutes(date, timeIntValue);
                break;
            case SECONDS:
                result = addSeconds(date, timeIntValue);
                break;
            case MILLISECONDS:
                result = addMilliseconds(date, timeIntValue);
                break;
            default:
                result = date;
        }
        return result;
    }

    /**
     * 追加年数
     *
     * @param date   日期，不为空
     * @param amount 追加的年数
     * @return 以追加的新日期
     */
    public static Date addYears(final Date date, final int amount) {
        return add(date, Calendar.YEAR, amount);
    }

    /**
     * 追加月数
     *
     * @param date   日期，不为空
     * @param amount 追加的周数
     * @return 以追加的新日期
     */
    public static Date addMonths(final Date date, final int amount) {
        return add(date, Calendar.MONTH, amount);
    }

    /**
     * 追加周数
     *
     * @param date   日期，不为空
     * @param amount 追加的周数
     * @return 以追加的新日期
     */
    public static Date addWeeks(final Date date, final int amount) {
        return add(date, Calendar.WEEK_OF_YEAR, amount);
    }

    /**
     * 追加天数
     *
     * @param date   日期，不为空
     * @param amount 追加的天数
     * @return 以追加的新日期
     */
    public static Date addDays(final Date date, final int amount) {
        return add(date, Calendar.DAY_OF_MONTH, amount);
    }

    /**
     * 追加小时
     *
     * @param date   日期，不为空
     * @param amount 追加的分钟数
     * @return 以追加的新日期
     */
    public static Date addHours(final Date date, final int amount) {
        return add(date, Calendar.HOUR_OF_DAY, amount);
    }

    /**
     * 追加分钟数
     *
     * @param date   日期，不为空
     * @param amount 追加的分钟数
     * @return 以追加的新日期
     */
    public static Date addMinutes(final Date date, final int amount) {
        return add(date, Calendar.MINUTE, amount);

    }

    /**
     * 追加秒数
     *
     * @param date   日期，不为空
     * @param amount 追加的秒数
     * @return 以追加的新日期
     */
    public static Date addSeconds(final Date date, final int amount) {
        return add(date, Calendar.SECOND, amount);
    }

    /**
     * 追加毫秒数，原对象不变
     *
     * @param date   日期不为空
     * @param amount 追加的毫秒数
     * @return 以追加的新日期
     */
    public static Date addMilliseconds(final Date date, final int amount) {
        return add(date, Calendar.MILLISECOND, amount);
    }


    private static Date add(Date date, int calendarField, int amount) {
        validateDateNotNull(date);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }


    private static void validateDateNotNull(Date date) {
        Validate.isTrue(date != null, "The date must not be null");
    }

    /**
     * 两个日期相差的时长，取绝对值
     *
     * @param startDate 开始时间，不为空
     * @param endDate   结束时间，不为空
     * @param msUnit    相差的时间单位
     * @return 相差的时长
     * @see DateMsUnit
     * @see #between(Date, Date, DateMsUnit)
     * @see Validate#notNull(Object, String, Object...)
     * @since 1.0.1
     */
    public static long between(Date startDate, Date endDate, DateMsUnit msUnit) {
        return between(startDate, endDate, msUnit, true);
    }

    /**
     * 两个日期相差的时长
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @param msUnit    相差的时间单位
     * @param isAbs     是否取绝对值
     * @return 相差的时长
     * @see DateMsUnit
     * @see #between(Date, Date, DateMsUnit)
     * @see Validate#notNull(Object, String, Object...)
     * @since 1.0.1
     */
    public static long between(Date startDate, Date endDate, DateMsUnit msUnit, boolean isAbs) {
        Validate.notNull(startDate, "start date must not null");
        Validate.notNull(endDate, "end date must not null");
        Validate.notNull(msUnit, "time unit must not null");
        Date begin = startDate;
        Date end = endDate;
        if (isAbs && begin.after(end)) {
            // 间隔只为正数的情况下，如果开始日期晚于结束日期，置换之
            begin = end;
            end = startDate;
        }
        long diff = end.getTime() - begin.getTime();
        return diff / msUnit.getMillis();
    }

    /**
     * 获取jvm启动时间
     *
     * @return 时间
     * @see ManagementFactory#getRuntimeMXBean()
     * @see RuntimeMXBean#getStartTime()
     * @since 1.0.1
     */
    public static Date getServerStartDate() {
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(startTime);
    }

    /**
     * 将date 转换成 {@link Calendar}实例
     *
     * @param date date
     * @return {@link Calendar}实例
     * @since 1.0.1
     */
    public static Calendar convertTo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * date1是否在date2之后<br>
     * 如果date1或者date2为null,永远返回false
     *
     * @param date1 date1
     * @param date2 date2
     * @return 是否在date2之后
     * @see Date#after(Date)
     * @since 1.0.2
     */
    public static boolean isAfter(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return false;
        }
        return date1.after(date2);
    }

    /**
     * 传入的时间是否在当前时间之后
     *
     * @param date date
     * @return 是否在当前时间之后
     * @since 1.0.2
     */
    public static boolean isAfter(Date date) {
        return isAfter(date, now());
    }

    /**
     * <code>date1</code>是否在<code>date2</code>之前<br>
     * 如果<code>date1</code>或者<code>date2</code>为null,永远返回<code>false</code>
     *
     * @param date1 date1
     * @param date2 date2
     * @return 是否在date2之前
     * @see Date#before(Date)
     * @since 1.0.2
     */
    public static boolean isBefore(Date date1, Date date2) {
        if (null == date1 || null == date2) {
            return false;
        }
        return date1.before(date2);
    }

    /**
     * 传入的时间是否在当前时间之前
     *
     * @param date date
     * @return 是否在当前时间之前
     * @since 1.0.2
     */
    public static boolean isBefore(Date date) {
        return isBefore(date, new Date());
    }
}
