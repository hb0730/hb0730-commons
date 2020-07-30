package com.hb0730.commons.lang;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * map util
 *
 * @author bing_huang
 * @date 2020/07/30 15:49
 * @since V1.0
 */
public class MapUtils {
    /**
     * 将map 转成 get 请求参数格式{@code XXX&XXX&XX}
     *
     * @param params map参数
     * @param encode 是否编码
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
}
