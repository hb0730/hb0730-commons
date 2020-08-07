package com.hb0730.commons.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exception Util
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class ExceptionUtils {
    /**
     * 获取异常信息
     *
     * @param throwable 异常
     * @return 异常信息
     */
    public static String getExceptionMessage(Throwable throwable) {
        final StringWriter sw = new StringWriter();
        final PrintWriter pw = new PrintWriter(sw, true);
        throwable.printStackTrace(pw);
        return sw.getBuffer().toString();
    }
}
