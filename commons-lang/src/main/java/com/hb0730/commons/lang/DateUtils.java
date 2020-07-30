package com.hb0730.commons.lang;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/07/30 14:29
 * @since V1.0
 */
public class DateUtils {
    /**
     * 获取当前时间
     *
     * @return 当前时间
     */
    public static Date now() {
        return new Date();
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
                result = org.apache.commons.lang3.time.DateUtils.addDays(date, timeIntValue);
                break;
            case HOURS:
                result = org.apache.commons.lang3.time.DateUtils.addHours(date, timeIntValue);
                break;
            case MINUTES:
                result = org.apache.commons.lang3.time.DateUtils.addMinutes(date, timeIntValue);
                break;
            case SECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addSeconds(date, timeIntValue);
                break;
            case MILLISECONDS:
                result = org.apache.commons.lang3.time.DateUtils.addMilliseconds(date, timeIntValue);
                break;
            default:
                result = date;
        }
        return result;
    }

}
