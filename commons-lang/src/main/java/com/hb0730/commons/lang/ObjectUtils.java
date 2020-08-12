package com.hb0730.commons.lang;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 对象操作类
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class ObjectUtils {
    public ObjectUtils() {
        super();
    }

    /**
     * 校验对象是否为<code>null</code>
     *
     * @param obj 需要校验的对象
     * @return true: 为<code>null</code>
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 校验对象是否非<code>null</code>
     *
     * @param obj 需要校验的对象
     * @return true:非<code>null</code>
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 校验指定对象是否为空，支持类型:
     * <pre>
     *     1. CharSequence
     *     2. Collection
     *     3. Map
     *     4. Array
     *     5. Optional
     *     6. Iterator
     * </pre>
     *
     * @param obj 需要校验的对象
     * @return true: 对象为空,false 不为空或者不支持
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj) {
        if (null == obj) {
            return true;
        }
        if (obj instanceof CharSequence) {
            return 0 == ((CharSequence) obj).length();
        } else if (obj instanceof Collection) {
            return ((Collection) obj).isEmpty();
        } else if (obj instanceof Map) {
            return ((Map) obj).isEmpty();
        } else if (obj.getClass().isArray()) {
            return 0 == Array.getLength(obj);
        } else if (obj instanceof Optional) {
            return !((Optional) obj).isPresent();
        } else if (obj instanceof Iterator) {
            return !((Iterator) obj).hasNext();
        }
        return false;
    }

    /**
     * 比较两个对象是否相等:
     * <pre>
     * 1. o1==null &amp;&amp; o2==null
     * 2. o1!=null &amp;&amp; o2.equals(o2)
     * </pre>
     *
     * @param o1 对象1
     * @param o2 对象2
     * @return true: 对象相等
     * @see Objects#equals(Object, Object)
     */
    public static boolean equal(Object o1, Object o2) {
        return Objects.equals(o1, o2);
    }

    /**
     * 比较两个对象是否不相等
     *
     * @param o1 对象1
     * @param o2 对象2
     * @return true:不相等
     * @see #equal(Object, Object)
     */
    public static boolean notEqual(Object o1, Object o2) {
        return !equal(o1, o2);
    }

    /**
     * 获取对象长度,如果对象为<code>null</code>,返回0，如果对象锁不支持的类型返回-1<br>
     * 支持类型如下:
     * <ul>
     * <li>CharSequence</li>
     * <li> Collection</li>
     * <li> Map</li>
     * <li> Array</li>
     * </ul>
     *
     * @param obj 被计算长度的对象
     * @return 长度，
     */
    @SuppressWarnings("rawtypes")
    public static int length(Object obj) {
        if (null == obj) {
            return 0;
        }
        if (obj instanceof CharSequence) {
            return ((CharSequence) obj).length();
        } else if (obj instanceof Collection) {
            return ((Collection) obj).size();
        } else if (obj instanceof Map) {
            return ((Map) obj).size();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj);
        }
        return -1;
    }

    /**
     * 比较两个对象的大小,
     *
     * @param t1  被比对象1
     * @param t2  被比参数2
     * @param <T> 实现了{@link Comparable}的类型
     * @return <ul>
     * <li>t1 &lt; t2 返回正数</li>
     * <li>t1 = t2 返回0</li>
     * <li>t1&gt;t2 返回负数</li>
     * </ul>
     * @see Comparable#compareTo(Object)
     * @see #compare(Comparable, Comparable, boolean)
     */
    public static <T extends Comparable<? super T>> int compare(T t1, T t2) {
        return compare(t1, t2, false);
    }

    /**
     * 比较两个对象的大小,
     *
     * @param t1          被比对象1
     * @param t2          被比参数2
     * @param nullGreater null最大
     * @param <T>         实现了{@link Comparable}的类型
     * @return <ul>
     * <li>t1 &lt; t2 返回正数</li>
     * <li>t1 = t2 返回0</li>
     * <li>t1&gt;t2 返回负数</li>
     * </ul>
     * @see Comparable#compareTo(Object)
     */
    public static <T extends Comparable<? super T>> int compare(T t1, T t2, boolean nullGreater) {
        if (t1 == t2) {
            return 0;
        }
        if (t1 == null) {
            return nullGreater ? 1 : -1;
        } else if (t2 == null) {
            return nullGreater ? -1 : 1;
        }
        return t1.compareTo(t2);
    }
}
