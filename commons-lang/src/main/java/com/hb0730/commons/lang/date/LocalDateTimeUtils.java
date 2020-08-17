package com.hb0730.commons.lang.date;

import com.hb0730.commons.lang.ObjectUtils;
import com.hb0730.commons.lang.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalUnit;
import java.util.Date;
import java.util.TimeZone;

/**
 * JDK8+中的{@link java.time.LocalDateTime} 工具类封装
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class LocalDateTimeUtils {
    /**
     * 当前时间,系统默认时区
     *
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }


    /**
     * 将{@link Instant}转换成{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param zoneId  时区{@link ZoneId}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, ZoneId zoneId) {
        if (null == instant) {
            return null;
        }
        return LocalDateTime.ofInstant(instant, ObjectUtils.defaultIfNull(zoneId, ZoneId.systemDefault()));
    }

    /**
     * 将{@link Instant}转换成{@link LocalDateTime}
     *
     * @param instant {@link Instant}
     * @param zone    时区{@link ZoneId}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, String zone) {
        ZoneId zoneId = StringUtils.isBlank(zone) ? ZoneId.systemDefault() : ZoneId.of(zone);
        return of(instant, zoneId);
    }

    /**
     * {@link Instant}转{@link LocalDateTime}，使用默认时区
     *
     * @param instant {@link Instant}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant) {
        return of(instant, ZoneId.systemDefault());
    }

    /**
     * {@link ZonedDateTime}转{@link LocalDateTime}
     *
     * @param zonedDateTime {@link ZonedDateTime}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(ZonedDateTime zonedDateTime) {
        if (null == zonedDateTime) {
            return null;
        }
        return zonedDateTime.toLocalDateTime();
    }

    /**
     * {@link Instant}转{@link LocalDateTime}
     *
     * @param instant  {@link Instant}
     * @param timeZone 时区
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Instant instant, TimeZone timeZone) {
        return of(instant, ObjectUtils.defaultIfNull(timeZone, TimeZone.getDefault()).toZoneId());
    }

    /**
     * 毫秒转{@link LocalDateTime}，结果会产生时间偏移
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param timeZone   时区,{@link TimeZone}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, TimeZone timeZone) {
        return of(Instant.ofEpochMilli(epochMilli), timeZone);
    }

    /**
     * 毫秒转{@link LocalDateTime}，结果会产生时间偏移
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @param timeZone   时区,{@link TimeZone}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli, String timeZone) {
        TimeZone zone = StringUtils.isBlank(timeZone) ? TimeZone.getDefault() : TimeZone.getTimeZone(timeZone);
        return of(epochMilli, zone);
    }

    /**
     * 毫秒转{@link LocalDateTime}，使用默认时区
     *
     * <p>注意：此方法使用默认时区，如果非UTC，会产生时间偏移</p>
     *
     * @param epochMilli 从1970-01-01T00:00:00Z开始计数的毫秒数
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(long epochMilli) {
        return of(epochMilli, TimeZone.getDefault());
    }

    /**
     * {@link Date}转{@link LocalDateTime}，使用默认时区
     *
     * @param date Date对象
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(Date date) {
        if (null == date) {
            return null;
        }
        return of(date.toInstant());
    }

    /**
     * {@link TemporalAccessor}转{@link LocalDateTime}，使用默认时区
     *
     * @param temporalAccessor {@link TemporalAccessor}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime of(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }
        if (temporalAccessor instanceof LocalDate) {
            return ((LocalDate) temporalAccessor).atStartOfDay();
        }
        return LocalDateTime.of(
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.YEAR),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.DAY_OF_MONTH),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.HOUR_OF_DAY),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.MINUTE_OF_HOUR),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.SECOND_OF_MINUTE),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.NANO_OF_SECOND)
        );
    }

    /**
     * {@link TemporalAccessor}转{@link LocalDate}，使用默认时区
     *
     * @param temporalAccessor {@link TemporalAccessor}
     * @return {@link LocalDate}
     */
    public static LocalDate ofDate(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }

        if (temporalAccessor instanceof LocalDateTime) {
            return ((LocalDateTime) temporalAccessor).toLocalDate();
        }

        return LocalDate.of(
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.YEAR),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.MONTH_OF_YEAR),
                TemporalAccessorUtils.get(temporalAccessor, ChronoField.DAY_OF_MONTH)
        );
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，格式支持日期时间、日期、时间
     *
     * @param datetime  日期时间字符串
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence datetime, DateTimeFormatter formatter) {
        if (null == datetime) {
            return null;
        }
        if (null == formatter) {
            return LocalDateTime.parse(datetime);
        }
        return of(formatter.parse(datetime));
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}，仅支持yyyy-MM-dd'T'HH:mm:ss格式，例如：2007-12-03T10:15:30
     *
     * @param datetime 日期时间字符串
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence datetime) {
        return parse(datetime, (DateTimeFormatter) null);
    }

    /**
     * 解析日期时间字符串为{@link LocalDateTime}
     *
     * @param datetime 日期时间字符串
     * @param format   日期格式，类似于yyyy-MM-dd HH:mm:ss,SSS
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime parse(CharSequence datetime, String format) {
        if (null == datetime) {
            return null;
        }
        return parse(datetime, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 解析日期时间字符串为{@link LocalDate}，格式支持日期
     *
     * @param date      日期时间字符串
     * @param formatter 日期格式化器，预定义的格式见：{@link DateTimeFormatter}
     * @return {@link LocalDate}
     */
    public static LocalDate parseDate(CharSequence date, DateTimeFormatter formatter) {
        if (null == date) {
            return null;
        }
        if (null == formatter) {
            return LocalDate.parse(date);
        }
        return ofDate(formatter.parse(date));
    }

    /**
     * 解析日期时间字符串为{@link LocalDate}，仅支持yyyy-MM-dd'T'HH:mm:ss格式，例如：2007-12-03T10:15:30
     *
     * @param date 日期时间字符串
     * @return {@link LocalDate}
     */
    public static LocalDate parseDate(CharSequence date) {
        return parseDate(date, (DateTimeFormatter) null);
    }

    /**
     * 解析日期字符串为{@link LocalDate}
     *
     * @param date   日期字符串
     * @param format 日期格式，类似于yyyy-MM-dd
     * @return {@link LocalDateTime}
     */
    public static LocalDate parseDate(CharSequence date, String format) {
        return parseDate(date, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param dateTime  {@link LocalDateTime}
     * @param formatter 日期格式，类似于yyyy-MM-dd HH:mm:ss,SSS
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime dateTime, DateTimeFormatter formatter) {
        return TemporalAccessorUtils.format(dateTime, formatter);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param dateTime {@link LocalDateTime}
     * @param format   日期格式，类似于yyyy-MM-dd HH:mm:ss,SSS
     * @return 格式化后的字符串
     */
    public static String format(LocalDateTime dateTime, String format) {
        return format(dateTime, DateTimeFormatter.ofPattern(format));
    }

    /**
     * 日期偏移,根据field不同加不同值（偏移会修改传入的对象）
     *
     * @param time   {@link LocalDateTime}
     * @param number 偏移量，正数为向后偏移，负数为向前偏移
     * @param field  偏移单位，见{@link ChronoField}，不能为null
     * @return 偏移后的日期时间
     */
    public static LocalDateTime offset(LocalDateTime time, long number, TemporalUnit field) {
        if (null == time) {
            return null;
        }

        return time.plus(number, field);
    }

    /**
     * 获取两个日期的差，如果结束时间早于开始时间，获取结果为负。
     * <p>
     * 返回结果为{@link Duration}对象，通过调用toXXX方法返回相差单位
     *
     * @param startTime 开始时间
     * @param endTime   结束时间
     * @return 时间差 {@link Duration}对象
     */
    public static Duration between(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime);
    }


    /**
     * 修改为一天的开始时间，例如：2020-02-02 00:00:00,000
     *
     * @param time 日期时间
     * @return 一天的开始时间
     */
    public static LocalDateTime beginOfDay(LocalDateTime time) {
        return time.with(LocalTime.of(0, 0, 0, 0));
    }

    /**
     * 修改为一天的结束时间，例如：2020-02-02 23:59:59,999
     *
     * @param time 日期时间
     * @return 一天的结束时间
     */
    public static LocalDateTime endOfDay(LocalDateTime time) {
        return time.with(LocalTime.of(23, 59, 59, 999_999_999));
    }

}
