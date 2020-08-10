package com.hb0730.commons.lang;

import com.hb0730.commons.lang.constants.SystemConst;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;

/**
 * 系统工具类
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class SystemUtils {

    /**
     * 获取属性信息
     *
     * @param key 标识
     * @return 结果
     */
    public static String getProperty(final String key) {
        return System.getProperty(key);
    }

    /**
     * 获取换行符号
     *
     * @return 换行符号
     */
    public static String getLineSeparator() {
        return getProperty(SystemConst.LINE_SEPARATOR);
    }

    /**
     * 获取运行时所有参数
     *
     * @return 运行时参数
     * @see RuntimeMXBean#getInputArguments()
     */
    public static List<String> getRuntimeArgs() {
        return ManagementFactory.getRuntimeMXBean().getInputArguments();
    }
}
