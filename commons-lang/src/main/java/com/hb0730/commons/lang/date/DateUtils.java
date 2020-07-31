package com.hb0730.commons.lang.date;

import com.hb0730.commons.lang.Validate;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Date utils
 *
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
}
