package com.hb0730.commons.lang;

/**
 * 类工具类
 *
 * @author bing_huang
 * @date 2020/07/30 17:26
 * @since V1.0
 */
public class ClassUtils {
    /**
     * 确定class是否可以被加载
     *
     * @param className   完整类名
     * @param classLoader 类加载
     * @return {boolean}
     */
    public static boolean isPresent(String className, ClassLoader classLoader) {
        try {
            Class.forName(className, true, classLoader);
            return true;
        } catch (Throwable ex) {
            return false;
        }
    }
}
