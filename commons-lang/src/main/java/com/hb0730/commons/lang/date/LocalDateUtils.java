package com.hb0730.commons.lang.date;

import com.hb0730.commons.lang.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.TimeZone;

/**
 * JDK8+中的{@link java.time.LocalDate} 工具类封装
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class LocalDateUtils {

    /**
     * 获取当前时间，默认时区
     *
     * @return 当前时间
     * @see ZoneId#systemDefault()
     */
    public static LocalDate now() {
        return now(ZoneId.systemDefault());
    }

    /**
     * 获取当前时间
     *
     * @param zoneId 时区id
     * @return 当前时间
     * @see TimeZone#getTimeZone(String)
     */
    public static LocalDate now(String zoneId) {
        ZoneId id;
        if (StringUtils.isBlank(zoneId)) {
            id = ZoneId.systemDefault();
        } else {
            TimeZone zone = TimeZone.getTimeZone(zoneId);
            id = zone.toZoneId();
        }
        return now(id);
    }

    /**
     * 获取当前时间
     *
     * @param zoneId 时区id
     * @return 当前时间
     */
    public static LocalDate now(ZoneId zoneId) {
        if (null == zoneId) {
            zoneId = ZoneId.systemDefault();
        }
        return LocalDate.now(zoneId);
    }

    /**
     * 构造{@link LocalDate}
     *
     * @param year 年
     * @param moth 月
     * @param day  日
     * @return {@link LocalDate}
     */
    public static LocalDate of(int year, int moth, int day) {
        return LocalDate.of(year, moth, day);
    }


}
