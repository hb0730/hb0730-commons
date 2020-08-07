package com.hb0730.commons.http;

import com.hb0730.commons.http.utils.HttpAsyncUtils;
import com.hb0730.commons.http.utils.HttpSyncUtils;

/**
 * commons utils list
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class CommonHttps {

    /**
     * 获取同步的utils,每一次调用都会新建，需要自行存储
     *
     * @return 实例化的 {@link HttpSyncUtils}
     * @see HttpSyncUtils
     */
    public static HttpSyncUtils sync() {
        return new HttpSyncUtils();
    }


    /**
     * 获取支持的异步utils,
     * 每一次调用都会新建，需要自行存储
     *
     * @return 实例化的 {@link HttpAsyncUtils}
     * @see HttpAsyncUtils
     * @see org.apache.hc.core5.http.message.BasicHttpResponse
     * @see okhttp3.Response
     */
    public static HttpAsyncUtils async() {
        return new HttpAsyncUtils();
    }
}
