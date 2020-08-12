package com.hb0730.commons.lang.reflect;

import com.hb0730.commons.lang.ClassUtils;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 反射
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class ReflectUtils {


    /**
     * 匹配getter方法的正则表达式
     */
    public static final Pattern GET_PATTERN = Pattern.compile("get(\\p{javaUpperCase}\\w*)");
    /**
     * 匹配setter方法的正则表达式
     */
    public static final Pattern SET_PATTERN = Pattern.compile("set(\\p{javaUpperCase}\\w*)");

    /**
     * 根据成员变量名称获取其值
     *
     * @param t           目标对象
     * @param targetField 属性字段名
     * @param <T>         目标对象类型
     * @return 成员变量值
     * @throws NoSuchFieldException   异常
     * @throws IllegalAccessException 异常
     */
    public static <T> Object getFieldValue(T t, String targetField) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = t.getClass();
        while (true) {
            try {
                Field field = clazz.getDeclaredField(targetField);
                field.setAccessible(true);
                return field.get(t);
            } catch (Exception e) {
                if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
                    clazz = clazz.getSuperclass();
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    /**
     * 设置对象字段值
     *
     * @param t     目标对象
     * @param field 属性字段
     * @param value 值
     * @param <T>   目标对象类型
     * @throws NoSuchFieldException   异常
     * @throws IllegalAccessException 异常
     */
    public static <T> void setFieldValue(T t, String field, Object value) throws NoSuchFieldException, IllegalAccessException {
        Class<?> clazz = t.getClass();
        while (true) {
            try {
                Field target = clazz.getDeclaredField(field);
                target.setAccessible(true);
                target.set(t, value);
                break;
            } catch (Exception e) {
                if (clazz.getSuperclass() != null && clazz.getSuperclass() != Object.class) {
                    clazz = clazz.getSuperclass();
                    continue;
                } else {
                    throw e;
                }
            }
        }
    }

    /**
     * 获取所有字段
     *
     * @param clazz              目标对象
     * @param containSupperClass 是否包含父项
     * @param <T>                目标对象类型
     * @return 所有目标对象的字段
     */
    public static <T> List<Field> getFields(Class<T> clazz, boolean containSupperClass) {
        if (null == clazz) {
            return null;
        }
        Field[] fields = clazz.getDeclaredFields();
        List<Field> list = new ArrayList<Field>(Arrays.asList(fields));

        if (containSupperClass) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().getSimpleName().equals(Object.class.getSimpleName())) {
                list.addAll(getFields(clazz.getSuperclass(), containSupperClass));
            }
        }
        return list;
    }

    /**
     * 获取所有方法
     *
     * @param clazz              目标对象
     * @param containSupperClass 是否包含父项
     * @param <T>                目标对象类型
     * @return 目标对象的所有方法
     */
    public static <T> List<Method> getMethods(Class<T> clazz, boolean containSupperClass) {
        if (null == clazz) {
            return null;
        }

        Method[] methods = clazz.getDeclaredMethods();
        List<Method> list = new ArrayList<Method>(Arrays.asList(methods));
        if (containSupperClass) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().getSimpleName().equals(Object.class.getSimpleName())) {
                list.addAll(getMethods(clazz.getSuperclass(), containSupperClass));
            }
        }
        return list;
    }

    /**
     * 查找指定方法 如果找不到对应的方法则返回<code>null</code>
     *
     * <p>
     * 此方法为精准获取方法名，即方法名和参数数量和类型必须一致，否则返回<code>null</code>。
     * </p>
     *
     * @param clazz      类，如果为{@code null}返回{@code null}
     * @param methodName 方法名，如果为空字符串返回{@code null}
     * @param paramTypes 参数类型，指定参数类型如果是方法的子类也算
     * @return 方法
     * @throws SecurityException 无权访问抛出异常
     * @since 1.0.2
     */
    public static Method getMethod(Class<?> clazz, String methodName, Class<?>... paramTypes) throws SecurityException {
        return getMethod(clazz, false, methodName, paramTypes);
    }


    /**
     * 查找指定方法 如果找不到对应的方法则返回<code>null</code>
     * <p></p>
     * 此方法为精准获取方法名，即方法名和参数数量和类型必须一致，否则返回<code>null</code>。
     *
     * @param clazz      类，如果为{@code null}返回{@code null}
     * @param ignoreCase 是否忽略大小写
     * @param methodName 方法名，如果为空字符串返回{@code null}
     * @param paramsType 参数类型，指定参数类型如果是方法的子类也算
     * @return 方法
     * @since 1.0.2
     */
    public static Method getMethod(Class<?> clazz, boolean ignoreCase, String methodName, Class<?>... paramsType) {
        if (null == clazz || StringUtils.isBlank(methodName)) {
            return null;
        }
        List<Method> methods = getMethods(clazz, true);
        if (!CollectionUtils.isEmpty(methods)) {
            for (Method method : methods) {
                if (StringUtils.equals(methodName, method.getName(), ignoreCase)) {
                    if (ClassUtils.isAllAssignableFrom(method.getParameterTypes(), paramsType)) {
                        return method;
                    }
                }
            }
        }
        return null;
    }

    /**
     * 获取对象所有的getter方法
     *
     * @param <T>                目标对象类型
     * @param clazz              需要被获取的对象
     * @param containSupperClass 是否包含父项getter
     * @return 对象所有的getter方法, 当对象为<code>null</code>时，返回<code>null</code>
     */
    public static <T> List<Method> getGetterMethods(Class<T> clazz, boolean containSupperClass) {
        if (null == clazz) {
            return null;
        }
        List<Method> getterMethods = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            Matcher m = GET_PATTERN.matcher(method.getName());
            if (m.matches()) {
                getterMethods.add(method);
            }
        }
        if (containSupperClass) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().getSimpleName().equals(Object.class.getSimpleName())) {
                getterMethods.addAll(getGetterMethods(clazz.getSuperclass(), containSupperClass));
            }
        }
        return getterMethods;
    }

    /**
     * 获取对象的setter方法。
     *
     * @param clazz              目标class
     * @param containSupperClass 是否包含父项
     * @param <T>                目标对象类型
     * @return 对象的setter方法列表，当目标对象为<code>null</code>，返回<code>null</code>
     */
    public static <T> List<Method> getSetterMethods(Class<T> clazz, boolean containSupperClass) {
        if (null == clazz) {
            return null;
        }
        List<Method> setterMethods = new ArrayList<Method>();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Matcher matcher = SET_PATTERN.matcher(method.getName());
            if (matcher.matches()) {
                setterMethods.add(method);
            }
        }
        if (containSupperClass) {
            if (clazz.getSuperclass() != null && !clazz.getSuperclass().getSimpleName().equals(Object.class.getSimpleName())) {
                setterMethods.addAll(getSetterMethods(clazz.getSuperclass(), containSupperClass));
            }
        }
        return setterMethods;
    }

    /**
     * 设置方法为可访问（私有方法可以被外部调用）
     *
     * @param <T>              AccessibleObject的子类，比如Class、Method、Field等
     * @param accessibleObject 可设置访问权限的对象，比如Class、Method、Field等
     * @return 被设置可访问的对象
     * @since 4.6.8
     */
    public static <T extends AccessibleObject> T setAccessible(T accessibleObject) {
        if (null != accessibleObject && !accessibleObject.isAccessible()) {
            accessibleObject.setAccessible(true);
        }
        return accessibleObject;
    }


    /**
     * 通过类的实例，调用指定的方法
     *
     * @param bean       对象
     * @param methodName 方法
     * @param args       参数对象
     * @param <T>        返回对象类型
     * @return 结果
     * @throws NoSuchMethodException     异常
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException    异常
     */
    public static <T> T invoke(Object bean, String methodName, Object... args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?>[] type = getMethodArgsType(args);
        Method method = null;
        if (null == type) {
            method = bean.getClass().getMethod(methodName);
            return invoke(bean, method);
        } else {
            method = bean.getClass().getMethod(methodName, type);
            return invoke(bean, method, args);
        }
    }

    /**
     * 通过类的实例，调用指定的方法
     *
     * @param bean   对象
     * @param method 方法
     * @param args   参数对象
     * @param <T>    返回对象类型
     * @return 结果
     * @throws InvocationTargetException 异常
     * @throws IllegalAccessException    异常
     */
    @SuppressWarnings({"unchecked"})
    public static <T> T invoke(Object bean, Method method, Object... args) throws InvocationTargetException, IllegalAccessException {
        if (null == bean || null == method) {
            return null;
        }
        return (T) method.invoke(bean, args);
    }

    /**
     * 根据参数获取参数类型
     *
     * @param args 参数
     * @return 参数类型
     */
    public static Class<?>[] getMethodArgsType(Object... args) {
        if (0 == args.length) {
            return null;
        }
        Class<?>[] clazz = new Class<?>[args.length];
        int index = 0;
        for (Object arg : args) {
            clazz[index] = arg.getClass();
            index++;
        }
        return clazz;
    }
}
