package com.hb0730.commons.lang.net;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.constants.PunctuationConst;
import com.hb0730.commons.lang.convert.ConverterRegistry;
import com.hb0730.commons.lang.exceptions.CommonsLangException;
import com.hb0730.commons.lang.map.MapUtils;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 参数构造器
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class QueryBuilder implements Builder<String> {
    private final Map<CharSequence, CharSequence> querys;
    private final Charset charset;

    /**
     * 构造 {@link QueryBuilder}
     *
     * @return {@link QueryBuilder}
     */
    public static QueryBuilder builder() {
        return new QueryBuilder();
    }

    /**
     * 构造 {@link QueryBuilder}
     *
     * @param charset 编码解码
     * @return {@link QueryBuilder}
     */
    public static QueryBuilder builder(Charset charset) {
        return new QueryBuilder(charset);
    }

    /**
     * 构造 {@link QueryBuilder}
     *
     * @param queryMap 初始化的查询键值对
     * @return {@link QueryBuilder}
     */
    public static QueryBuilder builder(Map<? extends CharSequence, ?> queryMap) {
        return new QueryBuilder(queryMap);
    }

    /**
     * 构造 {@link QueryBuilder}
     *
     * @param queryMap 初始化的查询键值对
     * @param charset  编码解码
     * @return {@link QueryBuilder}
     */
    public static QueryBuilder builder(Map<? extends CharSequence, ?> queryMap, Charset charset) {
        return new QueryBuilder(queryMap, charset);
    }

    /**
     * 构建{@link QueryBuilder}
     *
     * @param queryStr 初始化的查询字符串
     * @param charset  decode解码
     * @return {@link QueryBuilder}
     */
    public static QueryBuilder builder(String queryStr, Charset charset) {
        final QueryBuilder builder = new QueryBuilder(charset);
        builder.parse(queryStr);
        return builder;
    }

    /**
     * 构建{@link QueryBuilder}
     *
     * @param queryStr 初始化的查询字符串
     * @return {@link QueryBuilder}
     */
    public static QueryBuilder builder(String queryStr) {
        return builder(queryStr, null);
    }

    /**
     * 批量新增键值对
     *
     * @param queryMap 键值对
     * @return this
     */
    public QueryBuilder addAll(Map<? extends CharSequence, ?> queryMap) {
        if (CollectionUtils.isNotEmpty(queryMap)) {
            queryMap.forEach(this::add);
        }
        return this;
    }

    /**
     * 新增键值对
     *
     * @param key   键
     * @param value 值
     * @return this
     */
    public QueryBuilder add(CharSequence key, Object value) {
        this.querys.put(key, ConverterRegistry.getInstance().convert(String.class, value, null));
        return this;
    }

    /**
     * 获得查询的Map
     *
     * @return query map,只读，无法修改
     */
    public Map<CharSequence, CharSequence> getQuerys() {
        return MapUtils.unmodifiableMap(this.querys);
    }

    /**
     * 根据key获取值
     *
     * @param key key
     * @return 值
     */
    public CharSequence get(CharSequence key) {
        return this.querys.get(key);
    }

    /**
     * 构造
     *
     * @param queryMap 初始化的查询键值对
     * @param charset  编码与解码
     */
    public QueryBuilder(Map<? extends CharSequence, ?> queryMap, Charset charset) {
        this.charset = charset;
        if (CollectionUtils.isNotEmpty(queryMap)) {
            querys = new LinkedHashMap<>(queryMap.size());
            addAll(queryMap);
        } else {
            querys = new LinkedHashMap<>(MapUtils.DEFAULT_INITIAL_CAPACITY);
        }
    }

    /**
     * 构造
     *
     * @param charset 编码与解码
     */
    public QueryBuilder(Charset charset) {
        this(null, charset);
    }

    /**
     * 构造
     *
     * @param queryMap 初始化的查询键值对
     */
    public QueryBuilder(Map<? extends CharSequence, ?> queryMap) {
        this(queryMap, null);
    }

    /**
     * 构造
     */
    public QueryBuilder() {
        this(null, null);
    }


    /**
     * 构建URL查询字符串，即将key-value键值对转换为key1=v1&amp;key2=&amp;key3=v3形式
     *
     * @param charset encode编码
     * @return URL查询字符串
     */
    public String build(Charset charset) {
        if (CollectionUtils.isEmpty(this.querys)) {
            return PunctuationConst.EMPTY;
        }
        final StringBuilder builder = new StringBuilder();
        boolean isFirst = true;
        CharSequence key;
        CharSequence value;
        for (Map.Entry<CharSequence, CharSequence> entry : this.querys.entrySet()) {
            if (isFirst) {
                isFirst = false;
            } else {
                builder.append("&");
            }
            key = entry.getKey();
            if (StringUtils.isNotBlank(key)) {
                builder.append(key);
                value = entry.getValue();
                builder.append("=").append(value);
            }
        }

        String query = builder.toString();
        try {
            if (null != charset) {
                query = UrlUtils.urlEncode(query, charset);
            }
        } catch (UnsupportedEncodingException e) {
            throw new CommonsLangException(e);
        }
        return query;
    }

    @Override
    public String build() {
        return build(this.charset);
    }

    /**
     * 解析URL中的查询字符串
     *
     * @param queryStr 查询字符串，类似于key1=v1&amp;key2=&amp;key3=v3
     * @return this
     */
    public QueryBuilder parse(String queryStr) {
        if (StringUtils.isBlank(queryStr)) {
            return this;
        }
        int pathEndPos = queryStr.indexOf("?");
        String newStr = queryStr;
        if (pathEndPos > -1) {
            newStr = queryStr.substring(pathEndPos + 1);
        }
        //?q1=t1&q2=t2=&
        //?q1=t1&q2=t2&=
        //?q1=t1&q2=t2==
        String key = null;
        int pos = 0; //记录未处理字符开始位置
        int i; // 未处理字符结束位置
        char c; //记录当前字符
        //@see Hutool UrlQuery.java
        final int len = newStr.length();
        for (i = 0; i < len; i++) {
            c = newStr.charAt(i);
            switch (c) {
                case '=': //键和值的分界符
                    if (null == key) {
                        key = newStr.substring(pos, i);
                        // 开始位置从分节符后开始
                        pos = i + 1;
                    }
                    // 当=不作为分界符时，按照普通字符对待
                    break;
                case '&':
                    addParams(key, newStr.substring(pos, i));
                    key = null;
                    if (i + 4 < len && "amp;".equals(queryStr.substring(i + 1, i + 5))) {
                        // issue#850@Github，"&amp;"转义为"&"
                        i += 4;
                    }
                    pos = i + 1;
                    break;
                default:
                    break;
            }
        }
        addParams(key, newStr.substring(pos, i));
        return this;
    }

    /**
     * 将键值对加入到Map中，只有当Key不为空是
     *
     * @param key   key,不为null时加入Map
     * @param value value
     */
    private void addParams(CharSequence key, CharSequence value) {
        try {
            if (null != key) {
                String decodeKey = UrlUtils.decode(key.toString(), this.charset);
                String decodeValue = UrlUtils.decode(value.toString(), this.charset);
                this.querys.put(decodeKey, decodeValue);
            }
        } catch (UnsupportedEncodingException e) {
            throw new CommonsLangException(e);
        }

    }
}
