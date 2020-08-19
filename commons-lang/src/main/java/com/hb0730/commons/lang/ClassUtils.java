package com.hb0730.commons.lang;

import com.hb0730.commons.lang.collection.ArrayUtils;
import com.hb0730.commons.lang.convert.BasicTypeEnum;
import com.hb0730.commons.lang.reflect.TypeUtils;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

/**
 * 类工具类
 *
 * @author bing_huang
 * @since 1.0.0
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
     * 判断类是否为枚举类型
     *
     * @param clazz 类
     * @return 是否为枚举类型
     * @since 1.0.2
     */
    public static boolean isEnum(Class<?> clazz) {
        return null != clazz && clazz.isEnum();
    }

    /**
     * 是否为标准类<br>
     * <pre>
     * 1. 非接口
     * 2. 非枚举
     * 3. 非数组
     * 4. 非注解
     * 5. 非原始了下(int,log)
     * 6. 非抽象
     * </pre>
     *
     * @param clazz 类
     * @return 是否为标准类
     * @since 1.0.2
     */
    public static boolean isNormalClass(Class<?> clazz) {
        return null != clazz
                && !clazz.isInterface()
                && !clazz.isEnum()
                && !clazz.isArray()
                && !clazz.isAnnotation()
                && !clazz.isSynthetic()
                && !clazz.isPrimitive()
                && !isAbstract(clazz);
    }

    /**
     * 是否为抽象类
     *
     * @param clazz 类
     * @return 是否为抽象类
     * @since 1.0.2
     */
    public static boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    /**
     * 获得给定类的第一个泛型参数
     *
     * @param clazz 被检查的类，必须是已经确定泛型类型的类
     * @return {@link Class}
     * @since 1.0.2
     */
    public static Class<?> getTypeArgument(Class<?> clazz) {
        return getTypeArgument(clazz, 0);
    }

    /**
     * 获得给定类的泛型参数
     *
     * @param clazz 被检查的类，必须是已经确定泛型类型的类
     * @param index 泛型类型的索引号，即第几个泛型类型
     * @return {@link Class}
     * @since 1.0.2
     */
    public static Class<?> getTypeArgument(Class<?> clazz, int index) {
        final Type argumentType = TypeUtils.getTypeArgument(clazz, index);
        if (argumentType instanceof Class) {
            return (Class<?>) argumentType;
        }
        return null;
    }

    /**
     * 获取指定类型的默认值
     *
     * @param clazz class类
     * @return 默认值
     * @since 1.0.1
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

    /**
     * 判断该类型是不是包装类型,包含String
     *
     * @param clazz 类
     * @return 是否为包装类型
     * @since 1.0.1
     */
    public static boolean isBasicClass(Class<?> clazz) {
        boolean isPrimitive = false;
        try {
            if (clazz.isPrimitive() || clazz.isAssignableFrom(String.class)) {
                return true;
            } else {
                isPrimitive = ((Class<?>) clazz.getField("TYPE").get(null)).isPrimitive();
            }
        } catch (Exception e) {
            isPrimitive = false;
        }
        return isPrimitive;
    }

    /**
     * 判断该类型是否为基本类型（包括包装类和原始类）
     *
     * @param clazz 类
     * @return 是否为基本类型
     * @since 1.0.2
     */
    public static boolean isBasicType(Class<?> clazz) {
        if (null == clazz) {
            return false;
        }
        return clazz.isPrimitive() || isBasicClass(clazz);
    }

    /**
     * 比较判断types1和types2两组类，如果types1中所有的类都与types2对应位置的类相同，或者是其父类或接口，则返回<code>true</code>
     *
     * @param types1 类组1
     * @param types2 类组2
     * @return 是否相同、父类或接口
     * @since 1.0.2
     */
    public static boolean isAllAssignableFrom(Class<?>[] types1, Class<?>[] types2) {
        if (ArrayUtils.isEmpty(types1) && ArrayUtils.isEmpty(types2)) {
            return true;
        }
        if (null == types1 || null == types2) {
            return false;
        }
        if (types1.length != types2.length) {
            return false;
        }
        Class<?> type1;
        Class<?> type2;
        for (int i = 0; i < types1.length; i++) {
            type1 = types1[i];
            type2 = types2[i];
            if (isBasicType(type1) && isBasicType(type2)) {
                // 原始类型和包装类型存在不一致情况
                if (BasicTypeEnum.unWrap(type1) != BasicTypeEnum.unWrap(type2)) {
                    return false;
                }
            } else if (!type1.isAssignableFrom(type2)) {
                return false;
            }
        }
        return true;

    }
}
