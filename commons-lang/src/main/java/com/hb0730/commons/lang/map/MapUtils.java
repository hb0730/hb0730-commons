package com.hb0730.commons.lang.map;

import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.net.UrlUtils;

import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * map util
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class MapUtils {
    /**
     * 默认初始大小
     */
    public static final int DEFAULT_INITIAL_CAPACITY = 16;
    /**
     * 默认增长因子，当Map的size达到 容量*增长因子时，开始扩充Map
     */
    public static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 是否为空
     *
     * @param map map集合
     * @return 是否为空
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * 是否非空
     *
     * @param map map集合
     * @return 是否非空
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return null != map && !map.isEmpty();
    }

    /**
     * 是否为空，如果为空，返回一个{@link HashMap}
     *
     * @param map map集合
     * @param <K> key类型
     * @param <V> value类型
     * @return map
     * @since 2.0.4
     */
    public static <K, V> Map<K, V> defaultIfEmpty(Map<K, V> map) {
        return defaultIfEmpty(map, newHashMap());
    }

    /**
     * 是否为空,如果为空返回默认map
     *
     * @param map        map集合
     * @param defaultMap 默认map集合
     * @param <K>        key类型
     * @param <V>        value类型
     * @return map
     * @since 2.0.4
     */
    public static <K, V> Map<K, V> defaultIfEmpty(Map<K, V> map, Map<K, V> defaultMap) {
        if (isEmpty(map)) {
            return defaultMap;
        }
        return map;
    }


    /**
     * 新建map
     *
     * @param <K> key类型
     * @param <V> value类型
     * @return HashMap对象
     */
    public static <K, V> HashMap<K, V> newHashMap() {
        return new HashMap<>(16);
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
        return isOrder ? new LinkedHashMap<>(size) : new HashMap<>(size);
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
        return newHashMap(size, false);
    }

    /**
     * 新建TreeMap<br>
     * TreeMap key有序
     *
     * @param <K>        key的类型
     * @param <V>        value的类型
     * @param comparator Key比较器
     * @return TreeMap
     */
    public static <K, V> TreeMap<K, V> newTreeMap(Comparator<? super K> comparator) {
        return new TreeMap<>(comparator);
    }

    /**
     * 新建TreeMap<br>
     * Key有序的Map
     *
     * @param <K>        key的类型
     * @param <V>        value的类型
     * @param map        Map
     * @param comparator Key比较器
     * @return TreeMap
     */
    public static <K, V> TreeMap<K, V> newTreeMap(Map<K, V> map, Comparator<? super K> comparator) {
        final TreeMap<K, V> treeMap = new TreeMap<>(comparator);
        if (!isEmpty(map)) {
            treeMap.putAll(map);
        }
        return treeMap;
    }


    /**
     * 将map 转成 get 请求参数格式{@code XXX&XXX&XX}
     *
     * @param params map参数
     * @param encode 是否编码,使用UTF-8encode
     * @return str
     */
    public static String parseMapToUrlString(Map<String, String> params, boolean encode) {
        if (CollectionUtils.isEmpty(params)) {
            return "";
        }
        List<String> paramsList = new ArrayList<>();

        forEach(params, (k, v) -> {
            if (null == v) {
                paramsList.add(k + "=");
            } else {
                try {
                    paramsList.add(k + "=" + (encode ? UrlUtils.urlEncode(v) : v));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });
        return String.join("&", paramsList);
    }

    /**
     * 遍历
     *
     * @param map    待遍历的 map
     * @param action 操作
     * @param <K>    map键泛型
     * @param <V>    map值泛型
     */
    public static <K, V> void forEach(Map<K, V> map, BiConsumer<? super K, ? super V> action) {
        if (CollectionUtils.isEmpty(map) || action == null) {
            return;
        }
        for (Map.Entry<K, V> entry : map.entrySet()) {
            K k;
            V v;
            try {
                k = entry.getKey();
                v = entry.getValue();
            } catch (IllegalStateException ise) {
                // this usually means the entry is no longer in the map.
                throw new ConcurrentModificationException(ise);
            }
            action.accept(k, v);
        }
    }

    /**
     * map 排序,默认按照key排序
     *
     * @param map map
     * @param <K> key类型
     * @param <V> value类型
     * @return new TreeMap
     * @see #newTreeMap(Map, Comparator)
     */
    public static <K, V> TreeMap<K, V> sort(Map<K, V> map) {
        return sort(map, null);
    }

    /**
     * map排序,key 排序
     *
     * @param map        map
     * @param comparator key比较器
     * @param <K>        key类型
     * @param <V>        value类型
     * @return new TreeMap
     * @see #newTreeMap(Map, Comparator)
     */
    public static <K, V> TreeMap<K, V> sort(Map<K, V> map, Comparator<? super K> comparator) {
        if (null == map) {
            return null;
        }
        TreeMap<K, V> result;
        if (map instanceof TreeMap) {
            // 已经是可排序Map，此时只有比较器一致才返回原map
            result = (TreeMap<K, V>) map;
            if (null == comparator || comparator.equals(result.comparator())) {
                return result;
            }
        } else {
            result = newTreeMap(map, comparator);
        }

        return result;
    }

    /**
     * 将对应Map转换为不可修改的Map
     *
     * @param map Map
     * @param <K> 键类型
     * @param <V> 值类型
     * @return 不修改Map
     * @since 2.1.1
     */
    public static <K, V> Map<K, V> unmodifiableMap(Map<K, V> map) {
        return Collections.unmodifiableMap(map);
    }

}
