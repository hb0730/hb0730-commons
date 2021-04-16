package com.hb0730.commons.lang.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * {@link java.util.List}封装
 *
 * @author bing_huang
 * @since 1.0.2
 */
public class ListUtils {
    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param <T>      元素类型
     * @return {@link List}
     */
    public static <T> List<T> list(boolean isLinked) {
        return isLinked ? new LinkedList<>() : new ArrayList<>();
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked   是否实例化一个{@link LinkedList}
     * @param collection 元素集合
     * @param <T>        元素类型
     * @return {@link List}
     */
    public static <T> List<T> list(boolean isLinked, Collection<? extends T> collection) {
        if (null == collection) {
            return list(isLinked);
        }
        return isLinked ? new LinkedList<>(collection) : new ArrayList<>(collection);
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param values   元素数组
     * @param <T>      元素类型
     * @return {@link List}
     */
    @SafeVarargs
    public static <T> List<T> list(boolean isLinked, T... values) {
        if (ArrayUtils.isEmpty(values)) {
            return list(isLinked);
        }
        List<T> list = isLinked ? new LinkedList<>() : new ArrayList<>(values.length);
        Collections.addAll(list, values);
        return list;
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param iterable {@link Iterable}
     * @param <T>      元素类型
     * @return {@link List}
     */
    public static <T> List<T> list(boolean isLinked, Iterable<? extends T> iterable) {
        if (null == iterable) {
            return list(isLinked);
        }
        return list(isLinked, iterable.iterator());
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param iter     {@link Iterator}
     * @param <T>      元素类型
     * @return {@link List}
     */
    public static <T> List<T> list(boolean isLinked, Iterator<? extends T> iter) {
        final List<T> list = list(isLinked);
        if (null != iter) {
            while (iter.hasNext()) {
                list.add(iter.next());
            }
        }
        return list;
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link ArrayList}
     *
     * @param enumeration {@link Enumeration}
     * @param <T>         集合元素类型
     * @return ArrayList对象
     * @since 2.1.2
     */
    public static <T> List<T> list(Enumeration<T> enumeration) {
        return list(false, enumeration);
    }

    /**
     * 新建一个List<br>
     * 提供的参数为null时返回空{@link List}
     *
     * @param isLinked    是否为链表
     * @param enumeration {@link Enumeration}
     * @param <T>         集合元素类型
     * @return List对象
     * @since 2.1.2
     */
    public static <T> List<T> list(boolean isLinked, Enumeration<T> enumeration) {
        final List<T> data = list(isLinked);
        if (null != enumeration) {
            while (enumeration.hasMoreElements()) {
                data.add(enumeration.nextElement());
            }
        }
        return data;
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param collection 元素集合
     * @param <T>        元素类型
     * @return {@link ArrayList}对象
     */
    @SuppressWarnings({"unchecked"})
    public static <T> ArrayList<T> toArrayList(Collection<? extends T> collection) {
        return (ArrayList<T>) list(false, collection);
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param iterable {@link Iterable}
     * @param <T>      元素类型
     * @return {@link ArrayList}对象
     */
    @SuppressWarnings({"unchecked"})
    public static <T> ArrayList<T> toArrayList(Iterable<? extends T> iterable) {
        return (ArrayList<T>) list(false, iterable);
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param iterator {@link Iterator}
     * @param <T>      元素类型
     * @return {@link ArrayList}对象
     */
    @SuppressWarnings({"unchecked"})
    public static <T> ArrayList<T> toArrayList(Iterator<? extends T> iterator) {
        return (ArrayList<T>) list(false, iterator);
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param values 元素数组
     * @param <T>    元素类型
     * @return {@link ArrayList}对象
     */
    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... values) {
        return (ArrayList<T>) list(false, values);
    }

    /**
     * 实例化一个{@link LinkedList}
     *
     * @param values 元素数组
     * @param <T>    元素类型
     * @return {@link LinkedList}对象
     */
    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... values) {
        return (LinkedList<T>) list(true, values);
    }

    /**
     * 对list排序,修改原list
     *
     * @param list 需排序的list
     * @param c    比较器{@link Comparator}
     * @param <T>  元素类型
     * @return 已排序的list
     */
    public static <T> List<T> sort(List<T> list, Comparator<? super T> c) {
        list.sort(c);
        return list;
    }

    /**
     * 反序给定List，会在原List基础上直接修改
     *
     * @param <T>  元素类型
     * @param list 被反转的List
     * @return 反转后的List
     */
    public static <T> List<T> reverse(List<T> list) {
        Collections.reverse(list);
        return list;
    }

    /**
     * 获取一个空List
     *
     * @param <T> 元素类型
     * @return 空的List
     */
    public static <T> List<T> empty() {
        return Collections.emptyList();
    }
}
