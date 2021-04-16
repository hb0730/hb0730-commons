package com.hb0730.commons.lang.io;

import com.hb0730.commons.lang.ClassLoaderUtils;
import com.hb0730.commons.lang.collection.ListUtils;
import com.hb0730.commons.lang.constants.PathConst;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;

/**
 * Resource资源工具类
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class ResourcesUtils {
    /**
     * 获得资源的URL<br>
     * 路径用/分隔，例如:
     *
     * <pre>
     * config/a/db.config
     * spring/xml/test.xml
     * </pre>
     *
     * @param resource 资源（相对Classpath的路径）
     * @return 资源URL
     */
    public static URL getResource(String resource) {
        return getResource(resource, (Class<?>) null);
    }

    /**
     * 获得资源相对路径对应的URL
     *
     * @param resource  资源相对路径
     * @param baseClass 基准Class，获得的相对路径相对于此Class所在路径，如果为{@code null}则相对ClassPath
     * @return {@link URL}
     */
    public static URL getResource(String resource, Class<?> baseClass) {
        return (null != baseClass) ?
                baseClass.getResource(resource) :
                getResource(resource, (ClassLoader) null);
    }

    /**
     * 通过{@link ClassLoader}找到对应的resource资源URL
     *
     * @param location 路径
     * @param loader   {@link ClassLoader}
     * @return {@link URL}
     */
    public static URL getResource(String location, ClassLoader loader) {
        String path = location;
        if (path.startsWith(PathConst.ROOT_PATH)) {
            path = path.substring(1);
        }
        return (null == loader) ? ClassLoaderUtils.getClassLoader().getResource(path) : loader.getResource(path);
    }

    /**
     * 获取指定路径下的资源列表
     *
     * @param location 资源路径
     * @return 资源列表
     */
    public static List<URL> getResources(String location) {
        return getResources(location, null);
    }

    /**
     * 通过 {@link ClassLoader} 获取指定路径下的资源列表
     *
     * @param location 资源路径
     * @param loader   {@link ClassLoader}
     * @return 资源列表
     */
    public static List<URL> getResources(String location, ClassLoader loader) {
        String path = location;
        if (path.startsWith(PathConst.ROOT_PATH)) {
            path = path.substring(1);
        }
        Enumeration<URL> resources = null;
        try {
            resources = (null == loader) ? ClassLoaderUtils.getClassLoader().getResources(path) : loader.getResources(path);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
        return ListUtils.list(resources);
    }
}
