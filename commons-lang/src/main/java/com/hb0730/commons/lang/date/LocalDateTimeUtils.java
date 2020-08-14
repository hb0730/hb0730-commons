package com.hb0730.commons.lang.date;

import com.hb0730.commons.lang.ObjectUtils;
import com.hb0730.commons.lang.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
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

}
