package com.hb0730.commons.lang;

import com.hb0730.commons.lang.exceptions.CommonsLangException;
import com.hb0730.commons.lang.map.MapUtils;
import com.hb0730.commons.lang.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 枚举工具类
 *
 * @author bing_huang
 * @since 2.1.3
 */
public class EnumUtils {

    /**
     * 指定类是是否为Enum
     *
     * @param clazz class
     * @return 是否为Enum
     */
    public static boolean isEnum(Class<?> clazz) {
        return ClassUtils.isEnum(clazz);
    }

    /**
     * 指定类是否为Enum类
     *
     * @param obj 类
     * @return 是否为Enum类
     */
    public static boolean isEnum(Object obj) {
        return obj != null && obj.getClass().isEnum();
    }

    /**
     * 获得枚举类中各枚举对象下指定字段的值
     *
     * @param enumClass 枚举类
     * @param fieldName 字段名，最终调用getXXX方法
     * @return 字段值列表
     */
    @SuppressWarnings({"unchecked"})
    public static <T> List<T> getFiledValues(Class<? extends Enum<?>> enumClass, String fieldName) {
        if (!isEnum(enumClass)) {
            return null;
        }
        Enum<?>[] constants = enumClass.getEnumConstants();
        if (null == constants) {
            return null;
        }
        final List<T> values = new ArrayList<>(constants.length);
        try {
            for (Enum<?> e : constants) {
                values.add((T) ReflectUtils.getFieldValue(e, fieldName));
            }
        } catch (ReflectiveOperationException e) {
            throw new CommonsLangException(e);
        }

        return values;
    }

    /**
     * 获得枚举名对应指定字段值的Map<br>
     * 键为枚举名，值为字段值
     *
     * @param enumClass 枚举类
     * @param fieldName 字段名，最终调用getXXX方法
     * @return 枚举名对应指定字段值的Map
     */
    @SuppressWarnings({"unchecked"})
    public static <T> Map<String, T> getNameFieldMap(Class<? extends Enum<?>> enumClass, String fieldName) {
        if (!isEnum(enumClass)) {
            return null;
        }
        Enum<?>[] constants = enumClass.getEnumConstants();
        if (null == constants) {
            return null;
        }
        final Map<String, T> maps = MapUtils.newHashMap(constants.length);
        try {
            for (Enum<?> e : constants) {
                maps.put(e.name(), (T) ReflectUtils.getFieldValue(e, fieldName));
            }
        } catch (ReflectiveOperationException e) {
            throw new CommonsLangException(e);
        }
        return maps;
    }

    /**
     * 通过value找到对应的Enum类
     *
     * @param enumClass Enum类
     * @param value     value
     * @param <T>       value类型
     * @param <E>       Enum类型
     * @return value对应的Enum, 未找到返回为<code>null</code>
     */
    public static <T, E extends Enum<E>> E valueToEnum(Class<E> enumClass, T value) {
        return valueToEnum(enumClass, null, value);
    }

    /**
     * 通过filedName和value获取对应枚举
     *
     * @param enumClass 枚举类
     * @param filedName 字段名
     * @param value     值
     * @param <T>       值类型
     * @param <E>       枚举类型
     * @return 匹配到的枚举对象，未匹配到返回null
     */
    @SuppressWarnings({"unchecked"})
    public static <T, E extends Enum<E>> E valueToEnum(Class<E> enumClass, String filedName, T value) {
        if (null == enumClass) {
            return null;
        }
        final Enum<?>[] constants = enumClass.getEnumConstants();
        if (StringUtils.isNotBlank(filedName)) {
            try {
                for (Enum<?> e : constants) {
                    if (Objects.equals(value, ReflectUtils.getFieldValue(e, filedName))) {
                        return (E) e;
                    }
                }
            } catch (ReflectiveOperationException e) {
                throw new CommonsLangException(e);
            }
            return null;
        }
        final List<Field> fields = ReflectUtils.getFields(enumClass, false);
        String fieldName;
        try {
            for (Field field : fields) {
                fieldName = field.getName();
                for (Enum<?> e : constants) {
                    if (Objects.equals(value, ReflectUtils.getFieldValue(e, fieldName))) {
                        return (E) e;
                    }
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new CommonsLangException(e);
        }

        return null;
    }
}
