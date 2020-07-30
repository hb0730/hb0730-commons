package com.hb0730.commons.lang;

import java.util.Collection;
import java.util.Map;

/**
 * 集合
 *
 * @author bing_huang
 * @date 2020/07/30 13:46
 * @since V1.0
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
     * map 是否为空
     *
     * @param maps map
     * @return true: map 为null或者size为0
     */
    public static boolean isEmpty(Map<?, ?> maps) {
        return maps == null || maps.isEmpty();
    }
}
