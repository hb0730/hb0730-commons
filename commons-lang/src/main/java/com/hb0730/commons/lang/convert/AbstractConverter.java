package com.hb0730.commons.lang.convert;

import com.hb0730.commons.lang.CharUtils;
import com.hb0730.commons.lang.ClassUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;
import com.hb0730.commons.lang.convert.exceptions.ConverterException;

import java.util.Map;
import java.util.Optional;

/**
 * 抽象转换器，提供通用的转换逻辑，同时通过convertInternal实现对应类型的专属逻辑<br>
 * 转换器不会抛出转换异常，转换失败时会返回{@code null}
 *
 * @author bing_huang
 * @since 1.0.2
 */
public abstract class AbstractConverter<T> implements Converter<T> {
    /**
     * 不抛异常转换<br>
     * 当转换失败时返回默认值
     *
     * @param value        被转换的值
     * @param defaultValue 默认值
     * @return 转换后的值
     */
    public T convertQuietly(Object value, T defaultValue) {
        try {
            return convert(value, defaultValue);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public T convert(Object value, T defaultValue) throws ConverterException {
        Class<T> targetType = getTargetType();
        if (null == targetType && null == defaultValue) {
            throw new NullPointerException(String.format("[type] and [defaultValue] are both null for Converter [%s],we can not know what type to convert !", this.getClass().getName()));
        }
        if (null == targetType) {
            targetType = (Class<T>) defaultValue.getClass();
        }

        if (null == defaultValue || targetType.isInstance(defaultValue)) {
            if (targetType.isInstance(value) && !Map.class.isAssignableFrom(targetType)) {
                // 除Map外，已经是目标类型，不需要转换（Map类型涉及参数类型，需要单独转换）
                return targetType.cast(value);
            }
            T result = convertInternal(value);
            if (null == result) {
                return defaultValue;
            } else if (result instanceof Optional) {
                if (!((Optional) result).isPresent()) {
                    return defaultValue;
                } else {
                    return result;
                }
            } else {
                return result;
            }
        } else {
            throw new ConverterException(
                    String.format("Default value [%s](%s) is not the instance of [%s]", defaultValue, defaultValue.getClass(), targetType));
        }

    }

    /**
     * 内部转换器，被 {@link AbstractConverter#convert(Object, Object)} 调用，实现基本转换逻辑<br>
     * 内部转换器转换后如果转换失败可以做如下操作，处理结果都为返回默认值：
     *
     * <pre>
     * 1、返回{@code null}
     * 2、抛出一个{@link RuntimeException}异常
     * </pre>
     *
     * @param value 值
     * @return 转换后的类型
     * @throws ConverterException 转换异常
     */
    protected abstract T convertInternal(Object value) throws ConverterException;


    /**
     * 转字符串
     * <pre>
     * 1、字符串类型将被强转
     * 2、数组将被转换为逗号分隔的字符串
     * 3、其它类型将调用默认的toString()方法
     * </pre>
     *
     * @param value 值
     * @return 字符串
     */
    protected String toStr(Object value) {
        if (null == value) {
            return null;
        }
        if (value instanceof CharSequence) {
            return value.toString();
        } else if (ArrayUtils.isArray(value)) {
            return ArrayUtils.toString(value);
        } else if (CharUtils.isChar(value)) {
            return CharUtils.toString((char) value);
        }
        return value.toString();
    }

    /**
     * 获得此类实现类的泛型类型
     *
     * @return 此类的泛型类型，可能为{@code null}
     */
    @SuppressWarnings("unchecked")
    public Class<T> getTargetType() {
        return (Class<T>) ClassUtils.getTypeArgument(getClass());
    }

}
