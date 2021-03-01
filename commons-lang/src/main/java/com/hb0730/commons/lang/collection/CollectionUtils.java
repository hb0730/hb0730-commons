package com.hb0730.commons.lang.collection;

import com.hb0730.commons.lang.map.MapUtils;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 集合
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class CollectionUtils {

    /**
     * 集合是否为空
     *
     * @param collection 集合
     * @return true: 集合为空或者为null
     */
    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 集合不为空
     *
     * @param collection 集合
     * @return 集合不为空
     * @since 2.0.1
     */
    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    /**
     * map 是否为空
     *
     * @param maps map
     * @return true: map 为null或者size为0
     */
    public static boolean isEmpty(Map<?, ?> maps) {
        return maps == null || maps.isEmpty();
    }

    /**
     * map不为空
     *
     * @param maps map
     * @return map不为空
     * @since 2.0.1
     */
    public static boolean isNotEmpty(Map<?, ?> maps) {
        return !isEmpty(maps);
    }

    /**
     * 如果提供的集合为{@code null},返回一个不可变的默认空集合，否则返回原集合
     *
     * @param set 提供的集合，可能为{@code null}
     * @param <T> 元素类型
     * @return 原集合，若提供的集合为{@code null},则为空集合{@link Collections#emptySet()}
     * @since 1.0.2
     */
    public static <T> Set<T> emptyIfNull(Set<T> set) {
        return null == set ? Collections.emptySet() : set;
    }

    /**
     * 如果提供的集合为{@code null},返回一个不可变的默认空集合，否则返回原集合
     *
     * @param list 提供的集合，可能为{@code null}
     * @param <T>  元素类型
     * @return 原集合，若提供的集合为{@code null},则为空集合{@link Collections#emptyList()}
     * @since 1.0.2
     */
    public static <T> List<T> emptyIfNull(List<T> list) {
        return null == list ? Collections.emptyList() : list;
    }

    /**
     * 实例化一个{@link HashMap}
     *
     * @param <K> key类型
     * @param <V> value类型
     * @return {@link HashMap}对象
     * @since 1.0.2
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return MapUtils.newHashMap();
    }

    /**
     * 实例化一个{@link HashMap}
     *
     * @param size    初始化大小,由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75 + 1
     * @param isOrder 是否有序，有序则返回{@link LinkedHashMap}
     * @param <K>     key类型
     * @param <V>     value类型
     * @return {@link HashMap}
     * @since 1.0.2
     */
    public static <K, V> HashMap<K, V> newHashMap(int size, boolean isOrder) {
        return MapUtils.newHashMap(size, isOrder);
    }

    /**
     * 实例化一个{@link HashMap}
     *
     * @param size 初始化大小,由于默认负载因子0.75，传入的size会实际初始大小为size / 0.75 + 1
     * @param <K>  key类型
     * @param <V>  value类型
     * @return {@link HashMap}
     * @since 1.0.2
     */
    public static <K, V> HashMap<K, V> newHashMap(int size) {
        return MapUtils.newHashMap(size);
    }

    /**
     * 实例化一个{@link HashSet}
     *
     * @param isSorted 是否有序，有序则实例化{@link LinkedHashSet}
     * @param values   元素数组
     * @param <T>      元素类型
     * @return {@link HashSet}对象
     * @since 1.0.2
     */
    @SafeVarargs
    public static <T> HashSet<T> set(boolean isSorted, T... values) {
        if (null == values) {
            return isSorted ? new LinkedHashSet<>() : new HashSet<>();
        }
        int initialCapacity = Math.max((int) (values.length / .75f) + 1, 16);
        final HashSet<T> set = isSorted ? new LinkedHashSet<>(initialCapacity) : new HashSet<>(initialCapacity);
        Collections.addAll(set, values);
        return set;
    }

    /**
     * 实例化一个{@link HashSet}
     *
     * @param values 元素数组
     * @param <T>    元素类型
     * @return {@link HashSet}对象
     * @since 1.0.2
     */
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... values) {
        return set(false, values);
    }

    /**
     * 实例化一个{@link LinkedHashSet}
     *
     * @param values 元素数组
     * @param <T>    元素类型
     * @return {@link HashSet}对象
     * @since 1.0.2
     */
    @SafeVarargs
    public static <T> LinkedHashSet<T> newLinkedHashSet(T... values) {
        return (LinkedHashSet<T>) set(true, values);
    }

    /**
     * 实例化一个{@link HashSet}
     *
     * @param isSorted   是否有序，有序则实例化{@link LinkedHashSet}
     * @param collection 元素集合
     * @param <T>        元素类型
     * @return {@link HashSet}对象
     * @since 1.0.2
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Collection<? extends T> collection) {
        return isSorted ? new LinkedHashSet<>(collection) : new HashSet<>(collection);
    }

    /**
     * 实例化一个{@link HashSet}
     *
     * @param collection 元素集合
     * @param <T>        元素类型
     * @return {@link HashSet}对象
     * @since 1.0.2
     */
    public static <T> HashSet<T> newHashSet(Collection<? extends T> collection) {
        return newHashSet(false, collection);
    }

    /**
     * 实例化一个{@link HashSet}
     *
     * @param isSorted 是否有序，有序则实例化一个{@link LinkedHashSet}
     * @param iterator {@link Iterator}
     * @param <T>      元素类型
     * @return {@link HashSet}对象
     * @since 1.0.2
     */
    public static <T> HashSet<T> newHashSet(boolean isSorted, Iterator<? extends T> iterator) {
        if (null == iterator) {
            return set(isSorted, (T[]) null);
        }
        final HashSet<T> set = isSorted ? new LinkedHashSet<>() : new HashSet<>();
        while (iterator.hasNext()) {
            set.add(iterator.next());
        }
        return set;
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param <T>      元素类型
     * @return {@link List}
     * @since 1.0.2
     */
    public static <T> List<T> list(boolean isLinked) {
        return ListUtils.list(isLinked);
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked   是否实例化一个{@link LinkedList}
     * @param collection 元素集合
     * @param <T>        元素类型
     * @return {@link List}
     * @since 1.0.2
     */
    public static <T> List<T> list(boolean isLinked, Collection<? extends T> collection) {
        return ListUtils.list(isLinked, collection);
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param iterable {@link Iterable}
     * @param <T>      元素类型
     * @return {@link List}
     * @since 1.0.2
     */
    public static <T> List<T> list(boolean isLinked, Iterable<? extends T> iterable) {
        return ListUtils.list(isLinked, iterable);
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param iter     {@link Iterator}
     * @param <T>      元素类型
     * @return {@link List}
     * @since 1.0.2
     */
    public static <T> List<T> list(boolean isLinked, Iterator<? extends T> iter) {
        return ListUtils.list(isLinked, iter);
    }

    /**
     * 实例化一个{@link List}
     *
     * @param isLinked 是否实例化一个{@link LinkedList}
     * @param values   元素数组
     * @param <T>      元素类型
     * @return {@link List}
     * @since 1.0.2
     */
    @SafeVarargs
    public static <T> List<T> list(boolean isLinked, T... values) {
        return ListUtils.list(isLinked, values);
    }

    /**
     * 加入全部
     *
     * @param collection 被加入的集合 {@link Collection}
     * @param values     要加入的内容数组
     * @param <T>        集合元素类型
     * @return 原集合
     * @since 2.1.1
     */
    @SafeVarargs
    public static <T> Collection<T> addAll(Collection<T> collection, T... values) {
        if (null == collection || null == values) {
            return collection;
        }
        Collections.addAll(collection, values);
        return collection;
    }

    /**
     * 加入全部
     *
     * @param collection 被加入的集合 {@link Collection}
     * @param values     要加入的内容数组
     * @param <T>        集合元素类型
     * @return 原集合
     * @since 2.1.1
     */
    public static <T> Collection<T> addAll(Collection<T> collection, Collection<? extends T> values) {
        if (null == collection || null == values) {
            return collection;
        }
        collection.addAll(values);
        return collection;
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param collection 元素集合
     * @param <T>        元素类型
     * @return {@link ArrayList}对象
     * @since 1.0.2
     */
    public static <T> ArrayList<T> toArrayList(Collection<? extends T> collection) {
        return ListUtils.toArrayList(collection);
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param iterable {@link Iterable}
     * @param <T>      元素类型
     * @return {@link ArrayList}对象
     * @since 1.0.2
     */
    public static <T> ArrayList<T> toArrayList(Iterable<? extends T> iterable) {
        return ListUtils.toArrayList(iterable);
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param iterator {@link Iterator}
     * @param <T>      元素类型
     * @return {@link ArrayList}对象
     * @since 1.0.2
     */
    public static <T> ArrayList<T> toArrayList(Iterator<? extends T> iterator) {
        return ListUtils.toArrayList(iterator);
    }

    /**
     * 实例化一个{@link ArrayList}
     *
     * @param values 元素数组
     * @param <T>    元素类型
     * @return {@link ArrayList}对象
     * @since 1.0.2
     */
    @SafeVarargs
    public static <T> ArrayList<T> toArrayList(T... values) {
        return ListUtils.toArrayList(values);
    }

    /**
     * 实例化一个{@link LinkedList}
     *
     * @param values 元素数组
     * @param <T>    元素类型
     * @return {@link LinkedList}对象
     * @since 1.0.2
     */
    @SafeVarargs
    public static <T> LinkedList<T> toLinkedList(T... values) {
        return ListUtils.toLinkedList(values);
    }

    /**
     * 实例化一个{@link BlockingQueue}
     *
     * @param capacity 容量
     * @param isLinked 是否为链表队列
     * @param <T>      元素类型
     * @return {@link BlockingQueue}对象
     * @since 1.0.2
     */
    public static <T> BlockingQueue<T> toBlockingQueue(int capacity, boolean isLinked) {
        BlockingQueue<T> queue;
        if (isLinked) {
            queue = new LinkedBlockingQueue<>(capacity);
        } else {
            queue = new ArrayBlockingQueue<>(capacity);
        }
        return queue;
    }

    /**
     * 实例化一个,默认容量为16的{@link BlockingQueue}
     *
     * @param isLinked 是否为链表队列
     * @param <T>      元素类型
     * @return {@link BlockingQueue}对象
     * @since 1.0.2
     */
    public static <T> BlockingQueue<T> toBlockingQueue(boolean isLinked) {
        return toBlockingQueue(16, isLinked);
    }

    /**
     * 实例化一个,默认容量为16的{@link ArrayBlockingQueue}
     *
     * @param <T> 元素类型
     * @return {@link BlockingQueue}对象
     * @since 1.0.2
     */
    public static <T> BlockingQueue<T> toArrayBlockingQueue() {
        return toBlockingQueue(16, false);
    }

}
