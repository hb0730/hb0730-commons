package com.hb0730.commons.lang.bean;

import com.hb0730.commons.lang.reflect.ReflectUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * bean util
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class BeanUtils {
    /**
     * 深度克隆对象
     *
     * @param objSource 需要被clone的对象
     * @return 完成clone后的对象
     * @throws IllegalAccessException 异常
     * @throws InstantiationException 异常
     */
    public static Object deepClone(Object objSource) throws IllegalAccessException, InstantiationException {
        if (null == objSource) {
            return null;
        }
        // 获取源对象类型
        Class<?> clazz = objSource.getClass();
        Object objDes = clazz.newInstance();
        // 获得源对象所有属性
        List<Field> fields = ReflectUtils.getFields(clazz, true);
        // 循环遍历字段，获取字段对应的属性值
        for (Field field : fields) {
            field.setAccessible(true);
            // 如果该字段是 static + final 修饰
            if (field.getModifiers() >= 24) {
                continue;
            }
            try {
                // 设置字段可见，即可用get方法获取属性值。
                field.set(objDes, field.get(objSource));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return objDes;
    }

    /**
     * 获取bean字段描述,包含(getClass)
     *
     * @param clazz bean类
     * @return bean字段描述数组
     * @since 1.0.2
     */
    public static PropertyDescriptor[] getPropertyDescriptor(Class<?> clazz) throws BeanException {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
        } catch (IntrospectionException e) {
            e.printStackTrace();
            throw new BeanException(e);
        }
        return beanInfo.getPropertyDescriptors();
    }

    /**
     * 获得字段名和字段描述Map。内部使用，直接获取Bean类的PropertyDescriptor
     *
     * @param clazz      Bean类
     * @param ignoreCase 是否忽略大小写
     * @return 字段名和字段描述Map
     * @throws BeanException 获取属性异常
     * @since 1.0.2
     */
    public static Map<String, PropertyDescriptor> getPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) throws BeanException {
        Map<String, PropertyDescriptor> descriptorMap = BeanInfoCache.INSTANCE.getPropertyDescriptorMap(clazz, ignoreCase);
        if (null == descriptorMap) {
            descriptorMap = internalGetPropertyDescriptorMap(clazz, ignoreCase);
            BeanInfoCache.INSTANCE.putPropertyDescriptorMap(clazz, descriptorMap, ignoreCase);
        }
        return descriptorMap;
    }

    /**
     * 获得Bean类属性描述
     *
     * @param clazz      Bean类
     * @param fieldName  字段名
     * @param ignoreCase 是否忽略大小写
     * @return {@link PropertyDescriptor}
     * @throws BeanException 获取属性异常
     * @since 1.0.2
     */
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName, boolean ignoreCase) throws BeanException {
        final Map<String, PropertyDescriptor> map = getPropertyDescriptorMap(clazz, ignoreCase);
        return ignoreCase ? map.get(fieldName.toLowerCase()) : map.get(fieldName);
    }

    /**
     * 获得Bean类属性描述，大小写敏感
     *
     * @param clazz     Bean类
     * @param fieldName 字段名
     * @return {@link PropertyDescriptor}
     * @throws BeanException 获取属性异常
     * @since 1.0.2
     */
    public static PropertyDescriptor getPropertyDescriptor(Class<?> clazz, final String fieldName) throws BeanException {
        return getPropertyDescriptor(clazz, fieldName, false);
    }

    private static Map<String, PropertyDescriptor> internalGetPropertyDescriptorMap(Class<?> clazz, boolean ignoreCase) {
        PropertyDescriptor[] propertyDescriptors = getPropertyDescriptor(clazz);
        final Map<String, PropertyDescriptor> map = new ConcurrentHashMap<>((int) (propertyDescriptors.length), 1);
        if (ignoreCase) {
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                map.put(propertyDescriptor.getName().toLowerCase(), propertyDescriptor);
            }
        } else {
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                map.put(propertyDescriptor.getName(), propertyDescriptor);
            }
        }
        return map;
    }
}
