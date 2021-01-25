package com.hb0730.commons.http.support.httpclient;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.HttpException;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.support.callback.HttpCallback;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.map.MapUtils;
import lombok.Getter;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.*;
import org.apache.hc.core5.http.config.CharCodingConfig;
import org.apache.hc.core5.http.message.BasicHttpRequest;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.apache.hc.core5.http.nio.AsyncEntityConsumer;
import org.apache.hc.core5.http.nio.AsyncEntityProducer;
import org.apache.hc.core5.http.nio.entity.AsyncEntityProducers;
import org.apache.hc.core5.http.nio.entity.StringAsyncEntityConsumer;
import org.apache.hc.core5.http.nio.entity.StringAsyncEntityProducer;
import org.apache.hc.core5.http.nio.support.BasicRequestProducer;
import org.apache.hc.core5.http.nio.support.BasicResponseConsumer;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * HTTPClient async,需要自行关闭{@link #httpClient}
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class HttpClientAsyncImpl extends AbstractAsyncHttp {
    @Getter
    private final CloseableHttpAsyncClient httpClient;

    public HttpClientAsyncImpl() {
        this(HttpAsyncClients.createDefault(), new HttpConfig());
    }

    public HttpClientAsyncImpl(HttpConfig config) {
        this(HttpAsyncClients.createDefault(), config);
    }

    public HttpClientAsyncImpl(CloseableHttpAsyncClient httpClient, HttpConfig config) {
        super(config);
        this.httpClient = httpClient;
    }

    @Override
    public void get(String url, HttpCallback httpCallback) {
        get(url, null, httpCallback);
    }

    @Override
    public void get(String url, Map<String, String> params, HttpCallback httpCallback) {
        if (StringUtils.isBlank(url)) {
            throw new HttpException("request url must be not null");
        }
        String baseUrl = StringUtils.appendIfNotContain(url, "?", "&");
        url = baseUrl + MapUtils.parseMapToUrlString(params, httpConfig.isEncode());
        HttpGet httpGet = new HttpGet(url);
        addHeader(httpGet, header);
        StringAsyncEntityProducer entityProducer = new StringAsyncEntityProducer(Constants.EMPTY);
        BasicRequestProducer producer = new BasicRequestProducer(httpGet, entityProducer);
        this.exec(httpGet, producer, httpCallback);
    }

    @Override
    public void post(String url, final HttpCallback httpCallback) {
        post(url, Constants.EMPTY, httpCallback);
    }

    @Override
    public void post(String url, String dataJson, HttpCallback httpCallback) {
        if (StringUtils.isBlank(url)) {
            throw new HttpException("request url must be not null");
        }
        HttpPost httpPost = new HttpPost(url);
        addHeader(httpPost, header);
        AsyncEntityProducer entityProducer;
        if (StringUtils.isEmpty(dataJson)) {
            entityProducer = AsyncEntityProducers.create(Constants.EMPTY);
        } else {
            entityProducer = AsyncEntityProducers.create(dataJson, ContentType.APPLICATION_JSON);
        }
        BasicRequestProducer producer = new BasicRequestProducer(httpPost, entityProducer);
        this.exec(httpPost, producer, httpCallback);
    }

    @Override
    public void post(String url, Map<String, String> formdata, HttpCallback httpCallback) {
        if (StringUtils.isBlank(url)) {
            throw new HttpException("request url must be not null");
        }
        HttpPost httpPost = new HttpPost(url);
        addHeader(httpPost, header);
        AsyncEntityProducer entityProducer;
        if (!CollectionUtils.isEmpty(formdata)) {
            List<NameValuePair> requestData = new ArrayList<>();
            MapUtils.forEach(formdata, (k, v) -> requestData.add(new BasicNameValuePair(k, v)));
            entityProducer = AsyncEntityProducers.createUrlEncoded(requestData, Constants.DEFAULT_ENCODING);
        } else {
            entityProducer = AsyncEntityProducers.create(Constants.EMPTY);
        }

        BasicRequestProducer producer = new BasicRequestProducer(httpPost, entityProducer);
        this.exec(httpPost, producer, httpCallback);
    }

    /**
     * 设置请求头信息
     *
     * @param request 请求方式
     * @param header  请求头参数信息
     * @since 2.0.0
     */
    private void addHeader(BasicHttpRequest request, HttpHeader header) {
        if (null == request || null == header) {
            return;
        }
        Map<String, String> headers = header.getHeaders();
        if (CollectionUtils.isEmpty(headers)) {
            return;
        }
        MapUtils.forEach(headers, request::addHeader);
    }

    private void addHeader(BasicHttpRequest request) {
        if (null == request) {
            return;
        }
        String ua = Constants.USER_AGENT;
        Header[] headers = request.getHeaders(ua);
        if (null == headers || headers.length == 0) {
            request.setHeader(ua, Constants.USER_AGENT_DATA);
        }
    }

    private void exec(HttpUriRequestBase request, BasicRequestProducer producer, HttpCallback httpCallback) {
        this.addHeader(request);
        RequestConfig.Builder builder = RequestConfig.custom()
                .setConnectionRequestTimeout(httpConfig.getTimeout(), TimeUnit.MILLISECONDS)
                .setConnectTimeout(httpConfig.getTimeout(), TimeUnit.MILLISECONDS);
        if (null != httpConfig.getProxy()) {
            Proxy proxy = httpConfig.getProxy();
            InetSocketAddress address = (InetSocketAddress) proxy.address();
            HttpHost httpHost = new HttpHost(proxy.type().name().toLowerCase(), address.getHostName(), address.getPort());
            builder.setProxy(httpHost);
        }
        request.setConfig(builder.build());
        CharCodingConfig charCodingConfig = CharCodingConfig.custom().setCharset(Constants.DEFAULT_ENCODING).build();
        AsyncEntityConsumer<String> entityConsumer = new StringAsyncEntityConsumer(charCodingConfig);
        BasicResponseConsumer<String> consumer = new BasicResponseConsumer<>(entityConsumer);
        this.httpClient.start();
        this.httpClient.execute(producer, consumer, new FutureCallback<Message<HttpResponse, String>>() {
            @SneakyThrows
            @Override
            public void completed(Message<HttpResponse, String> result) {
                if (null == httpCallback) {
                    return;
                }
                HttpResponse head = result.getHead();
                if (head.getCode() >= HttpStatus.SC_SUCCESS && head.getCode() < HttpStatus.SC_REDIRECTION) {
                    httpCallback.success(result.getBody());
                }
            }

            @Override
            public void failed(Exception ex) {
                if (null == httpCallback) {
                    return;
                }
                httpCallback.failure(ex);
            }

            @Override
            public void cancelled() {

            }
        });
        //注意关闭Client(自行关闭)
//        try {
//            future.get(httpConfig.getTimeout(), TimeUnit.MILLISECONDS);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            this.httpClient.close(CloseMode.GRACEFUL);
//        }

    }
}
