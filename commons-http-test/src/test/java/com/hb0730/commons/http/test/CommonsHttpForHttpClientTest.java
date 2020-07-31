package com.hb0730.commons.http.test;

import com.hb0730.commons.http.HttpUtils;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.support.httpclient.HttpClientImpl;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * @author bing_huang
 * @date 2020/07/31 11:10
 * @since V1.0
 */
public class CommonsHttpForHttpClientTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonsHttpForHttpClientTest.class);
    /**
     * 链接超时
     **/
    private static final int CONNECT_TIMEOUT = 10000;
    private static final int SOCKET_TIMEOUT = 10000;
    /**
     * 最大连接数
     */
    private static final int MAX_CONN = 32;

    private static final int MAX_PRE_ROUTE = 200;
    private static final int MAX_ROUTE = 100;

    @Before
    public void init() {
        HttpUtils.setHttp(new HttpClientImpl(createHttpClient()));
    }

    @Test
    public void httpClientTest() {
        HttpUtils.setHttpConfig(HttpConfig.builder().build());
        System.out.println(HttpUtils.get("https://www.baidu.com/search/error.html"));
    }


    private CloseableHttpClient createHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAX_CONN);
        connectionManager.setDefaultMaxPerRoute(MAX_PRE_ROUTE);
        //请求失败时,进行请求重试
        HttpRequestRetryHandler handler = (e, i, httpContext) -> {
            LOGGER.error("request error, message:{}", e.getMessage());
            if (i > 5) {
                //重试超过3次,放弃请求
                LOGGER.error("retry has more than 5 time, give up request");
                return false;
            }
            if (e instanceof NoHttpResponseException) {
                //服务器没有响应,可能是服务器断开了连接,应该重试
                LOGGER.error("receive no response from server, retry");
                return true;
            }
            if (e instanceof SSLHandshakeException) {
                // SSL握手异常
                LOGGER.error("SSL hand shake exception");
                return false;
            }
            if (e instanceof InterruptedIOException) {
                //超时
                LOGGER.error("InterruptedIOException");
                return false;
            }
            if (e instanceof UnknownHostException) {
                // 服务器不可达
                LOGGER.error("server host unknown");
                return false;
            }
            if (e instanceof ConnectTimeoutException) {
                // 连接超时
                LOGGER.error("Connection Time out");
                return false;
            }
            if (e instanceof SSLException) {
                LOGGER.error("SSLException");
                return false;
            }

            HttpClientContext context = HttpClientContext.adapt(httpContext);
            HttpRequest request = context.getRequest();
            //如果请求不是关闭连接的请求
            return !(request instanceof HttpEntityEnclosingRequest);
        };
        return HttpClients.custom().setConnectionManager(connectionManager).build();
    }
}
