package com.hb0730.commons.lang;

/**
 * {@link ClassLoader}工具类
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class ClassLoaderUtils {

    /**
     * 获取当前线程的{@link ClassLoader}
     *
     * @return 当前线程的Class loader
     * @see Thread#getContextClassLoader()
     */
    public static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }

    /**
     * 获取{@link ClassLoader},获取顺序如下
     * <pre>
     *     1. 获取当前线程的ContextClassLoader
     *     2. 获取当前类对应的ClassLoader
     *     3. 获取系统的ClassLoader {@link ClassLoader#getSystemClassLoader()}
     * </pre>
     *
     * @return 类加载器
     */
    public static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (null == classLoader) {
            classLoader = ClassLoaderUtils.class.getClassLoader();
            if (null == classLoader) {
                classLoader = ClassLoader.getSystemClassLoader();
            }
        }
        return classLoader;
    }


}
