package com.hb0730.commons.lang.runtime;

import com.hb0730.commons.lang.constants.SystemConst;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 系统工具类
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class SystemUtils {
    private static AtomicInteger shutdownHookThreadIndex = new AtomicInteger(0);

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
     * 获取路径分隔符
     *
     * @return 路径分隔符
     * @since 2.1.1
     */
    public static String getPathSeparator() {
        return getProperty(SystemConst.PATH_SEPARATOR);
    }
    /// RunTimeMxBean////

    /**
     * 获取运行时的进程Pid
     *
     * @return Pid
     * @since 1.0.2
     */
    public static int getPid() {
        // format: "pid@hostname"
        String jvmName = ManagementFactory.getRuntimeMXBean().getName();
        String[] split = jvmName.split("@");
        if (split.length != 2) {
            return -1;
        }
        return Integer.parseInt(split[0]);
    }

    /**
     * 获取应用启动当现在的毫秒数
     *
     * @return 毫秒数
     * @since 1.0.2
     */
    public static long getUpTime() {
        return ManagementFactory.getRuntimeMXBean().getUptime();
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

    //// Runtime ////

    /**
     * 获取cpu核数
     *
     * @return cpu核数
     * @since 1.0.2
     */
    public static int getCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 注册JVM关闭时的钩子程序
     *
     * @param runnable {@link Runnable}
     * @since 1.0.2
     */
    public static void addShutdownHook(Runnable runnable) {
        Runtime.getRuntime().addShutdownHook(new Thread(runnable, "Thread-ShutDownHook" + shutdownHookThreadIndex.incrementAndGet()));
    }
}
