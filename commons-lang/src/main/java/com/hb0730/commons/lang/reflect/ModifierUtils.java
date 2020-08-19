package com.hb0730.commons.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 修饰符工具类
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class ModifierUtils {

    /**
     * 是否存在访问修饰符
     *
     * @param clazz        类
     * @param modifierType 访问修饰符
     * @return 是否存在
     */
    public static boolean hasModifier(Class<?> clazz, ModifierType modifierType) {
        if (null == clazz || null == modifierType) {
            return false;
        }
        return 0 != (clazz.getModifiers() & modifierType.value);
    }

    /**
     * 是否存在访问修饰符
     *
     * @param constructor  构造函数
     * @param modifierType 访问修饰符
     * @return 是否存在
     */
    public static boolean hasModifier(Constructor<?> constructor, ModifierType modifierType) {
        if (null == constructor || null == modifierType) {
            return false;
        }
        return 0 != (constructor.getModifiers() & modifierType.value);
    }

    /**
     * 是否存在访问修饰符
     *
     * @param method       方法
     * @param modifierType 访问修饰符
     * @return 是否存在
     */
    public static boolean hasModifier(Method method, ModifierType modifierType) {
        if (null == method || null == modifierType) {
            return false;
        }
        return 0 != (method.getModifiers() & modifierType.value);
    }

    /**
     * 是否存在访问修饰符
     *
     * @param field        属性字段
     * @param modifierType 访问修饰符
     * @return 是否存在
     */
    public static boolean hasModifier(Field field, ModifierType modifierType) {
        if (null == field || null == modifierType) {
            return false;
        }
        return 0 != (field.getModifiers() & modifierType.value);
    }


    /**
     * 是否是Public字段
     *
     * @param field 字段
     * @return 是否是Public
     */
    public static boolean isPublic(Field field) {
        return hasModifier(field, ModifierType.PUBLIC);
    }

    /**
     * 是否是Public方法
     *
     * @param method 方法
     * @return 是否是Public
     */
    public static boolean isPublic(Method method) {
        return hasModifier(method, ModifierType.PUBLIC);
    }

    /**
     * 是否是Public构造方法
     *
     * @param constructor 构造方法
     * @return 是否是Public
     */
    public static boolean isPublic(Constructor<?> constructor) {
        return hasModifier(constructor, ModifierType.PUBLIC);
    }

    /**
     * 是否是Public类
     *
     * @param clazz 类
     * @return 是否是Public
     */
    public static boolean isPublic(Class<?> clazz) {
        return hasModifier(clazz, ModifierType.PUBLIC);
    }

    /**
     * 是否是static字段
     *
     * @param field 字段
     * @return 是否是static
     */
    public static boolean isStatic(Field field) {
        return hasModifier(field, ModifierType.STATIC);
    }

    /**
     * 是否是static方法
     *
     * @param method 方法
     * @return 是否是static
     */
    public static boolean isStatic(Method method) {
        return hasModifier(method, ModifierType.STATIC);
    }

    /**
     * 是否是static类
     *
     * @param clazz 类
     * @return 是否是static
     */
    public static boolean isStatic(Class<?> clazz) {
        return hasModifier(clazz, ModifierType.STATIC);
    }

//    /**
//     * 多个修饰符做“与”操作，表示同时存在多个修饰符
//     *
//     * @param modifierTypes 修饰符列表，元素不能为空
//     * @return “与”之后的修饰符
//     */
//    private static int modifiersToInt(ModifierType... modifierTypes) {
//        int modifier = modifierTypes[0].getValue();
//        for (int i = 1; i < modifierTypes.length; i++) {
//            modifier += modifierTypes[i].getValue();
//        }
//        return modifier;
//    }

    /**
     * 修饰符枚举
     *
     * @author bing_huang
     * @since 1.0.2
     */
    public enum ModifierType {
        /**
         * public,所有类都能访问
         */
        PUBLIC(Modifier.PUBLIC),
        /**
         * private,只能被自己访问和修改
         */
        PRIVATE(Modifier.PRIVATE),

        /**
         * protected,自身、子类及同一个包中类可以访问
         */
        PROTECTED(Modifier.PROTECTED),
        /**
         * static,（静态修饰符）指定变量被所有对象共享，即所有实例都可以使用该变量。变量属于这个类
         */
        STATIC(Modifier.STATIC),
        /**
         * final,最终修饰符，指定此变量的值不能变，使用在方法上表示不能被重载
         */
        FINAL(Modifier.FINAL),

        /**
         * synchronize,在多个线程中，该修饰符用于在运行前，对他所属的方法加锁，以防止其他线程的访问，运行结束后解锁。
         */
        SYNCHRONIZE(Modifier.SYNCHRONIZED),

        /**
         * volatile,指定该变量可以同时被几个线程控制和修改
         */
        VOLATILE(Modifier.VOLATILE),

        /**
         * transient,指定该变量是系统保留，暂无特别作用的临时性变量，序列化时忽略
         */
        TRANSIENT(Modifier.TRANSIENT),
        /**
         * native,指定此方法的方法体是用其他语言在程序外部编写的
         */
        NATIVE(Modifier.NATIVE),
        /**
         * interface,将一个类声明为接口，所有方法为抽象方法，需要子类提供实现
         */
        INTERFACE(Modifier.INTERFACE),
        /**
         * abstract, 将一个类声明为抽象类，没有实现的方法，需要子类提供方法实现。
         */
        ABSTRACT(Modifier.ABSTRACT),

        /**
         * strictfp,一旦使用了关键字strictfp来声明某个类、接口或者方法时，那么在这个关键字所声明的范围内所有浮点运算都是精确的，符合IEEE-754规范的。
         */
        STRICT(Modifier.STRICT),
        ;

        /**
         * 访问修饰符对应的int修饰符值
         */
        private final int value;

        ModifierType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
}
