package com.hb0730.commons.http.support.httpclient;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.collection.MapUtils;
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
import org.apache.hc.core5.io.CloseMode;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * HTTPClient async
 *
 * @author bing_huang
 * @date 2020/08/05 10:40
 * @since V1.0
 */
public class HttpClientAsyncImpl extends AbstractAsyncHttp {
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
    public void get(String url, CommonsNetCall commonsNetCall) {
        get(url, null, commonsNetCall, null);
    }

    @Override
    public void get(String url, CommonsNetCall commonsNetCall, Map<String, String> params) {
        get(url, new HttpHeader(), commonsNetCall, params);
    }

    @Override
    public void get(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> params) {
        String baseUrl = StringUtils.appendIfNotContain(url, "?", "&");
        url = baseUrl + MapUtils.parseMapToUrlString(params, httpConfig.isEncode());
        HttpGet httpGet = new HttpGet(url);
        StringAsyncEntityProducer entityProducer = new StringAsyncEntityProducer(Constants.EMPTY);
        BasicRequestProducer producer = new BasicRequestProducer(httpGet, entityProducer);
        this.exec(httpGet, producer, commonsNetCall);
    }

    @Override
    public void post(String url, final CommonsNetCall commonsNetCall) {
        post(url, Constants.EMPTY, commonsNetCall);
    }

    @Override
    public void post(String url, String data, CommonsNetCall commonsNetCall) {
        post(url, data, null, commonsNetCall);
    }

    @Override
    public void post(String url, String data, HttpHeader header, CommonsNetCall commonsNetCall) {
        HttpPost httpPost = new HttpPost(url);
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), httpPost::addHeader);
        }
        AsyncEntityProducer entityProducer;
        if (StringUtils.isEmpty(data)) {
            entityProducer = AsyncEntityProducers.create(Constants.EMPTY);
        } else {
            entityProducer = AsyncEntityProducers.create(data, ContentType.APPLICATION_JSON);
        }
        BasicRequestProducer producer = new BasicRequestProducer(httpPost, entityProducer);
        this.exec(httpPost, producer, commonsNetCall);
    }

    @Override
    public void post(String url, CommonsNetCall commonsNetCall, Map<String, String> formdata) {
        post(url, null, commonsNetCall, formdata);
    }

    @Override
    public void post(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> formdata) {
        HttpPost httpPost = new HttpPost(url);
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), httpPost::addHeader);
        }
        AsyncEntityProducer entityProducer;
        if (!CollectionUtils.isEmpty(formdata)) {
            List<NameValuePair> requestData = new ArrayList<>();
            MapUtils.forEach(formdata, (k, v) -> requestData.add(new BasicNameValuePair(k, v)));
            entityProducer = AsyncEntityProducers.createUrlEncoded(requestData, Constants.DEFAULT_ENCODING);
        } else {
            entityProducer = AsyncEntityProducers.create(Constants.EMPTY);
        }

        BasicRequestProducer producer = new BasicRequestProducer(httpPost, entityProducer);
        this.exec(httpPost, producer, commonsNetCall);
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

    private void exec(HttpUriRequestBase request, BasicRequestProducer producer, CommonsNetCall commonsNetCall) {
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
        Future<Message<HttpResponse, String>> future = this.httpClient.execute(producer, consumer, new FutureCallback<Message<HttpResponse, String>>() {
            @SneakyThrows
            @Override
            public void completed(Message<HttpResponse, String> result) {
                HttpResponse head = result.getHead();
                if (head.getCode() >= 200 && head.getCode() < 300) {
                    commonsNetCall.success(result.getBody());
                }
            }

            @Override
            public void failed(Exception ex) {
                commonsNetCall.file(ex);
            }

            @Override
            public void cancelled() {

            }
        });
        try {
            future.get(httpConfig.getTimeout(), TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            this.httpClient.close(CloseMode.GRACEFUL);
        }

    }
}
