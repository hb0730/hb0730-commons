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

    /**
     * 获取指定类型的默认值
     *
     * @param clazz class类
     * @return 默认值
     */
    public static Object getDefaultValue(Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        if (clazz.isPrimitive()) {
            if (byte.class == clazz) {
                return (byte) 0;
            }
            if (short.class == clazz) {
                return (short) 0;
            }
            if (int.class == clazz) {
                return 0;
            }
            if (long.class == clazz) {
                return 0L;
            }
            if (double.class == clazz) {
                return 0D;
            }
            if (float.class == clazz) {
                return 0f;
            }
            if (boolean.class == clazz) {
                return false;
            }
            if (char.class == clazz) {
                return (char) 0;
            }
        }
        return null;
    }
}
