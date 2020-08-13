package com.hb0730.commons.lang.convert;

import com.hb0730.commons.lang.convert.impl.*;
import com.hb0730.commons.lang.reflect.ReflectUtils;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 转换器存储
 *
 * @author bing_huang
 */
public class ConverterRegistry {
    /**
     * 默认类型转换器
     */
    private Map<Type, Converter<?>> defaultConverterMap;
    /**
     * 用户自定义类型转换器
     */
    private volatile Map<Type, Converter<?>> customConverterMap;

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private static class SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        private static final ConverterRegistry INSTANCE = new ConverterRegistry();
    }


    /**
     * 获得单例的 {@link ConverterRegistry}
     *
     * @return {@link ConverterRegistry}
     */
    public static ConverterRegistry getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public ConverterRegistry() {
        defaultConverter();
    }

    /**
     * 等级自定义转换器
     *
     * @param type           转换的目标类型
     * @param converterClass 转换器类
     * @return {@link ConverterRegistry} 转换器存储
     */
    public ConverterRegistry putCustom(Type type, Class<? extends Converter<?>> converterClass) {
        return putCustom(type, ReflectUtils.newInstance(converterClass));
    }

    /**
     * 等级自定义转换器
     *
     * @param type      转换的目标类型
     * @param converter 转换器类
     * @return {@link ConverterRegistry} 转换器存储
     */
    public ConverterRegistry putCustom(Type type, Converter<?> converter) {
        if (null == this.customConverterMap) {
            synchronized (this) {
                if (null == this.customConverterMap) {
                    customConverterMap = new ConcurrentHashMap<>();
                }
            }
        }
        this.customConverterMap.put(type, converter);
        return this;
    }

    /**
     * 获得转换器<br>
     *
     * @param <T>           转换的目标类型
     * @param type          类型
     * @param isCustomFirst 是否自定义转换器优先
     * @return 转换器
     */
    public <T> Converter<T> getConverter(Type type, boolean isCustomFirst) {
        Converter<T> converter;
        if (isCustomFirst) {
            converter = this.getCustomConverter(type);
            if (null == converter) {
                converter = this.getDefaultConverter(type);
            }
        } else {
            converter = this.getDefaultConverter(type);
            if (null == converter) {
                converter = this.getCustomConverter(type);
            }
        }
        return converter;
    }

    /**
     * 获取默认转换器
     *
     * @param type 转换器类型
     * @param <T>  转换的目标类型
     * @return 转换器
     */
    @SuppressWarnings("unchecked")
    public <T> Converter<T> getDefaultConverter(Type type) {
        return null == type ? null : (Converter<T>) this.defaultConverterMap.get(type);
    }

    /**
     * 获取自定义转换器
     *
     * @param type 转换器类型
     * @param <T>  转换的目标类型
     * @return 目标转换器
     */
    @SuppressWarnings("unchecked")
    public <T> Converter<T> getCustomConverter(Type type) {
        return null == type ? null : (Converter<T>) this.customConverterMap.get(type);
    }


    public ConverterRegistry defaultConverter() {
        defaultConverterMap = new ConcurrentHashMap<>();

        defaultConverterMap.put(byte.class, new PrimitiveConverter(byte.class));
        defaultConverterMap.put(short.class, new PrimitiveConverter(short.class));
        defaultConverterMap.put(int.class, new PrimitiveConverter(int.class));
        defaultConverterMap.put(long.class, new PrimitiveConverter(long.class));
        defaultConverterMap.put(float.class, new PrimitiveConverter(float.class));
        defaultConverterMap.put(double.class, new PrimitiveConverter(double.class));
        defaultConverterMap.put(char.class, new PrimitiveConverter(char.class));
        defaultConverterMap.put(boolean.class, new PrimitiveConverter(boolean.class));

        defaultConverterMap.put(Number.class, new NumberConverter());
        defaultConverterMap.put(Integer.class, new NumberConverter(Integer.class));
        defaultConverterMap.put(Long.class, new NumberConverter(Long.class));
        defaultConverterMap.put(Byte.class, new NumberConverter(Byte.class));
        defaultConverterMap.put(Short.class, new NumberConverter(Short.class));
        defaultConverterMap.put(Float.class, new NumberConverter(Float.class));
        defaultConverterMap.put(Double.class, new NumberConverter(Double.class));
        defaultConverterMap.put(String.class, new StringConverter());
        defaultConverterMap.put(CharSequence.class, new StringConverter());
        defaultConverterMap.put(Boolean.class, new BooleanConverter());
        defaultConverterMap.put(Character.class, new CharacterConverter());
        return this;
    }
}
