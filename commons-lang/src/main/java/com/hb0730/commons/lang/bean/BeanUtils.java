package com.hb0730.commons.lang.bean;

import com.hb0730.commons.lang.reflect.ReflectUtils;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @author bing_huang
 * @date 2020/08/05 9:07
 * @since V1.0
 */
public class BeanUtils {
    /**
     * 深度克隆对象
     *
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
}
