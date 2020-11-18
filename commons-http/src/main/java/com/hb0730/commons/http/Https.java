package com.hb0730.commons.http;

import com.hb0730.commons.http.inter.Http;
import com.hb0730.commons.http.utils.HttpAsync;
import com.hb0730.commons.http.utils.HttpSync;

/**
 * commons utils list
 *
 * @author bing_huang
 * @since 1.0.0
 */
public enum Https {
    /**
     * 同步请求
     *
     * @see HttpSync
     */
    SYNC(new HttpSync()),
    /**
     * 异步请求
     *
     * @see HttpAsync
     */
    ASYNC(new HttpAsync());

    private final Http http;

    Https(Http t) {
        this.http = t;
    }

    /**
     * 获取当前的请求类型,{@link Http}的子类 {@link HttpSync} 或者 {@link HttpAsync}
     *
     * @param <T> {@link Http}
     * @return {@link Http}的子类
     */
    @SuppressWarnings({"unchecked"})
    public <T extends Http> T getHttp() {
        return (T) http;
    }
}
