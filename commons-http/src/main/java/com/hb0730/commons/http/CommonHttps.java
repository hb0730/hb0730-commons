package com.hb0730.commons.http;

import com.hb0730.commons.http.utils.HttpAsync;
import com.hb0730.commons.http.utils.HttpSync;

/**
 * commons utils list
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class CommonHttps {
    public static HttpSync sync = new HttpSync();
    public static HttpAsync async = new HttpAsync();

    /**
     * 获取同步Sync
     *
     * @return 实例化的 {@link HttpSync}
     * @see HttpSync
     */
    public static HttpSync sync() {
        return sync;
    }

    /**
     * 获取支持的异步utils,
     * 每一次调用都会新建，需要自行存储
     *
     * @return 实例化的 {@link HttpAsync}
     * @see HttpAsync
     */
    public static HttpAsync async() {
        return async;
    }
}
