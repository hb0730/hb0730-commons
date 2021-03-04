package com.hb0730.commons.spring;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.io.IOException;

/**
 * spring boot 获取资源工具类
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class ResourcesUtils {


    /**
     * 获取多资源，url格式: <code>classpath*:/config/*.yml</code>
     *
     * @param path 路径
     * @return {@link Resource}
     * @throws IOException io异常
     * @see PathMatchingResourcePatternResolver
     */
    public static Resource[] getResources(@NotNull String path) throws IOException {
        return getResources(path, null);
    }

    /**
     * 获取多资源,url格式: <code>classpath*:/config/*.yml</code>
     *
     * @param path   路径
     * @param loader {@link ClassLoader}
     * @return {@link Resource}
     * @throws IOException io异常
     * @see PathMatchingResourcePatternResolver
     */
    public static Resource[] getResources(@NotNull String path, @Nullable ClassLoader loader) throws IOException {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver(loader);
        return resolver.getResources(path);
    }

    /**
     * 获取resources下资源
     * <pre>
     * //src/main/resources/resource.yaml
     * ResourcesUtils.getResource("resource.yaml")
     * </pre>
     *
     * @param path 资源路径
     * @return {@link Resource}
     * @see ClassPathResource
     */
    public static Resource getResource(@NotNull String path) {
        return getResource(path, null);
    }

    /**
     * 获取resources下资源
     * <pre>
     * //src/main/resources/resource.yaml
     * ResourcesUtils.getResource("resource.yaml",null)
     * </pre>
     *
     * @param loader {@link ClassLoader}
     * @param path   资源路径
     * @return {@link Resource}
     * @see ClassPathResource
     */
    public static Resource getResource(@NotNull String path, @Nullable ClassLoader loader) {
        return new ClassPathResource(path, loader);
    }

}
