package com.hb0730.commons.spring;

import org.springframework.aop.framework.AopContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;

/**
 * spring context工具类
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class SpringContextUtils {
    private static ApplicationContext applicationContext;

    /**
     * 获取spring context
     *
     * @return spring context
     */
    public static ApplicationContext getApplicationContext() {
        Assert.notNull(applicationContext, "Please set it first ApplicationContext");
        return applicationContext;
    }

    /**
     * 设置spring context
     *
     * @param applicationContext spring context
     */
    public static void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }

    /**
     * 获取spring beanFactory的bean
     *
     * @param name bean name
     * @param <T>  bean 类型
     * @return bean实例
     * @throws BeansException bean exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) throws BeansException {
        return (T) getApplicationContext().getBean(name);
    }

    /**
     * 获取spring beanFactory中bean
     *
     * @param clz class bean
     * @param <T> bean 类型
     * @return bean实例
     * @throws BeansException bean exception
     */

    public static <T> T getBean(Class<T> clz) throws BeansException {
        return getApplicationContext().getBean(clz);
    }

    /**
     * spring beanFactory是否存在该bean
     *
     * @param name bean name
     * @return true 存在,false 不存在
     */
    public static boolean containsBean(String name) {
        return getApplicationContext().containsBean(name);
    }

    /**
     * bean 是否为单例
     *
     * @param name bean name
     * @return true 为单例
     * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
     */
    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().isSingleton(name);
    }

    /**
     * 根据名称获取注册类型
     *
     * @param name bean  name
     * @return 注册类型
     * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
     */
    public static Class<?> getType(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getType(name);
    }

    /**
     * 获取bean的别名
     *
     * @param name bean name
     * @return bean 别名
     * @throws NoSuchBeanDefinitionException NoSuchBeanDefinitionException
     */
    public static String[] getAliases(String name) throws NoSuchBeanDefinitionException {
        return getApplicationContext().getAliases(name);
    }

    /**
     * 获取aop代理对象
     *
     * @param <T>     代理类型
     * @param invoker 当前代理类型
     * @return 当前代理
     */
    @SuppressWarnings("unchecked")
    public static <T> T getAopProxy(T invoker) {
        return (T) AopContext.currentProxy();
    }
}
