package com.hb0730.commons.spring;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.aop.support.AopUtils;

/**
 * 关于aop 代理 工具类
 *
 * @author bing_huang
 * @see AopProxyUtils
 * @see AopUtils
 * @since 2.0.4
 */
public class AopTargetUtils {

    /**
     * 获取JDK动态代理/CGLIB代理对象代理的目标对象
     *
     * @param proxy 代理对象
     * @return 目标对象
     */
    public static Object getTarget(Object proxy) {

        if (!AopUtils.isAopProxy(proxy)) {
            return proxy;
        }
        return AopProxyUtils.getSingletonTarget(proxy);
    }
}
