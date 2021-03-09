package com.hb0730.commons.lang.net;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.constants.PathConst;
import com.hb0730.commons.lang.constants.PunctuationConst;
import com.hb0730.commons.lang.exceptions.CommonsLangException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.LinkedList;

/**
 * URL中Path部分的封装
 *
 * @author bing_huang
 * @since 2.1.1
 */
public class PathBuilder implements Builder<String> {

    private final LinkedList<String> paths = new LinkedList<>();
    /**
     * 是否以/结尾
     */
    private boolean withEngTag;
    /**
     * 编码与解码
     */
    private Charset charset;

    /**
     * 构建{@link PathBuilder}
     *
     * @param path    初始路径
     * @param charset 编码与解码
     * @return {@link PathBuilder}
     */
    public static PathBuilder builder(String path, Charset charset) {
        final PathBuilder builder = (null == charset ? new PathBuilder() : new PathBuilder(charset));
        builder.parse(path, charset);
        return builder;
    }

    /**
     * 设置是否以/结尾
     *
     * @param withEngTag 是否以/结尾
     * @return /
     */
    public PathBuilder setWithEngTag(boolean withEngTag) {
        this.withEngTag = withEngTag;
        return this;
    }

    /**
     * 添加路径
     *
     * @param path 路径
     * @return this
     */
    public PathBuilder addAfter(String path) {
        addInternal(fixPath(path), false);
        return this;
    }

    /**
     * 添加到path最前面
     *
     * @param path Path节点
     * @return this
     */
    public PathBuilder addBefore(String path) {
        addInternal(fixPath(path), true);
        return this;
    }

    /**
     * 解析path
     *
     * @param path    路径，类似于aaa/bb/ccc或/aaa/bbb/ccc
     * @param charset decode编码，null表示不解码
     * @return this
     */
    public PathBuilder parse(String path, Charset charset) {
        if (!StringUtils.isEmpty(path)) {
            if (path.endsWith(PathConst.ROOT_PATH)) {
                withEngTag = true;
            }
            String newPath = fixPath(path);
            final String[] split = newPath.split(PathConst.ROOT_PATH);
            try {
                for (String seg : split) {
                    if (null == charset) {
                        addInternal(seg, false);
                    } else {
                        addInternal(URLDecoder.decode(seg, charset.name()), false);
                    }
                }
            } catch (UnsupportedEncodingException e) {
                throw new CommonsLangException(e);
            }

        }
        return this;
    }

    /**
     * 构建path，前面带'/'
     *
     * @param charset charset encode编码，null表示不做encode
     * @return 如果没有任何内容，则返回空字符串""
     */
    public String build(Charset charset) {
        if (CollectionUtils.isEmpty(paths)) {
            return PunctuationConst.EMPTY;
        }
        final StringBuilder builder = new StringBuilder();
        try {
            for (String path : paths) {
                builder.append(PathConst.ROOT_PATH);
                if (null == charset) {
                    builder.append(path);
                } else {
                    builder.append(URLEncoder.encode(path, charset.name()));
                }
            }
        } catch (UnsupportedEncodingException e) {
            throw new CommonsLangException(e);
        }
        if (withEngTag || builder.length() == 0) {
            builder.append(PathConst.ROOT_PATH);
        }
        return builder.toString();

    }

    /**
     * 构造函数
     *
     * @param charset 编码与解码
     */
    public PathBuilder(Charset charset) {
        this.charset = charset;
    }

    /**
     * 构造函数
     */
    public PathBuilder() {

    }

    @Override
    public String build() {
        return build(this.charset);
    }

    /**
     * 增加节点
     *
     * @param path   节点
     * @param before 是否在前面添加
     */
    private void addInternal(String path, boolean before) {
        if (before) {
            paths.add(0, path);
        } else {
            paths.add(path);
        }
    }

    /**
     * 除去前后/结尾
     *
     * @param path 路径
     * @return 修正后的路径
     */
    private String fixPath(String path) {
        if (StringUtils.isBlank(path)) {
            return PunctuationConst.EMPTY;
        }
        path = StringUtils.removePrefix(path, PathConst.ROOT_PATH);
        path = StringUtils.removeSuffix(path, PathConst.ROOT_PATH);
        return path.trim();
    }
}
