package com.hb0730.commons.http.test;

import com.hb0730.commons.http.CommonHttps;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl;
import com.hb0730.commons.http.utils.HttpAsyncUtils;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManager;
import org.apache.hc.client5.http.impl.nio.PoolingAsyncClientConnectionManagerBuilder;

/**
 * @author bing_huang
 * @date 2020/08/06 17:26
 * @since V1.0
 */
public class CommonsHttpClientPoolUtils {
    /**
     * 最大连接数
     */
    private static final int MAX_CONN = 32;
    private static final int MAX_PRE_ROUTE = 200;
    private static final int MAX_ROUTE = 100;

    private static PoolingAsyncClientConnectionManager connectionManager;
    private static CloseableHttpAsyncClient asyncClient;
    private static HttpAsyncUtils asyncUtils;
    private final static Object SYNC_LOCK = new Object();

    static {
        connectionManager = PoolingAsyncClientConnectionManagerBuilder.create()
                .setMaxConnTotal(MAX_CONN)
                .setMaxConnPerRoute(MAX_PRE_ROUTE)
                .build();
    }

    public static HttpAsyncUtils async() {
        if (null == asyncClient) {
            synchronized (SYNC_LOCK) {
                if (null == asyncUtils) {
                    asyncClient = HttpAsyncClients
                            .custom()
                            .setConnectionManager(connectionManager)
                            .build();

                    asyncUtils = CommonHttps.async().setHttp(new HttpClientAsyncImpl(asyncClient, new HttpConfig()));
                }
            }
        }
        return asyncUtils;
    }
}
