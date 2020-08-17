package com.hb0730.commons.lang.date;

import com.hb0730.commons.lang.StringUtils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;

/**
 * {@link java.time.temporal.TemporalAccessor}封装
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class TemporalAccessorUtils {
    /**
     * 安全获取时间的某个属性，属性不存在返回0
     *
     * @param temporalAccessor 需要获取的时间对象
     * @param field            需要获取的属性
     * @return 时间的值，如果无法获取则默认为 0
     */
    public static int get(TemporalAccessor temporalAccessor, TemporalField field) {
        if (temporalAccessor.isSupported(field)) {
            return temporalAccessor.get(field);
        }
        return (int) field.range().getMinimum();
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param temporalAccessor 日期时间
     * @param formatter        格式化,{@link DateTimeFormatter}
     * @return 指定后的格式
     */
    public static String format(TemporalAccessor temporalAccessor, DateTimeFormatter formatter) {
        if (null == temporalAccessor) {
            return null;
        }
        if (null == formatter) {
            formatter = DateTimeFormatter.ISO_DATE_TIME;
        }
        return formatter.format(temporalAccessor);
    }

    /**
     * 格式化日期时间为指定格式
     *
     * @param temporalAccessor 日期时间{@link TemporalAccessor}
     * @param formatter        日期格式
     * @return 格式化后的字符串
     */
    public static String format(TemporalAccessor temporalAccessor, String formatter) {
        DateTimeFormatter format = StringUtils.isBlank(formatter) ? null : DateTimeFormatter.ofPattern(formatter);
        return format(temporalAccessor, format);
    }

    /**
     * {@link TemporalAccessor}转换为 {@link Instant}对象
     *
     * @param temporalAccessor Date对象
     * @return {@link Instant}对象
     */
    public static Instant toInstant(TemporalAccessor temporalAccessor) {
        if (null == temporalAccessor) {
            return null;
        }
        Instant result;
        if (temporalAccessor instanceof Instant) {
            result = (Instant) temporalAccessor;
        } else if (temporalAccessor instanceof LocalDateTime) {
            result = ((LocalDateTime) temporalAccessor).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof ZonedDateTime) {
            result = ((ZonedDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof OffsetDateTime) {
            result = ((OffsetDateTime) temporalAccessor).toInstant();
        } else if (temporalAccessor instanceof LocalDate) {
            result = ((LocalDate) temporalAccessor).atStartOfDay(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof LocalTime) {
            result = ((LocalTime) temporalAccessor).atDate(LocalDate.now()).atZone(ZoneId.systemDefault()).toInstant();
        } else if (temporalAccessor instanceof OffsetTime) {
            result = ((OffsetTime) temporalAccessor).atDate(LocalDate.now()).toInstant();
        } else {
            result = Instant.from(temporalAccessor);
        }
        return result;
    }

}
