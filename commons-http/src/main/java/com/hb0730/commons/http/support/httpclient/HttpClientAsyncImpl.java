package com.hb0730.commons.http.support.httpclient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.CommonHttpException;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.lang.collection.MapUtils;
import lombok.SneakyThrows;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequest;
import org.apache.hc.client5.http.async.methods.SimpleHttpRequests;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
import org.apache.hc.client5.http.config.RequestConfig;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.Header;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.BasicHttpRequest;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    private ObjectMapper objectMapper;

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
        SimpleHttpRequest request = SimpleHttpRequests.get(url);
        if (!CollectionUtils.isEmpty(params) || null != header) {
            MapUtils.forEach(header.getHeaders(), request::addHeader);
        }
        this.exec(request, commonsNetCall);
    }

    @Override
    public void post(String url, final CommonsNetCall commonsNetCall) {
        post(url, "", commonsNetCall);
    }

    @Override
    public void post(String url, String data, CommonsNetCall commonsNetCall) {
        post(url, data, null, commonsNetCall);
    }

    @Override
    public void post(String url, String data, HttpHeader header, CommonsNetCall commonsNetCall) {
        SimpleHttpRequest request = SimpleHttpRequests.post(url);
        if (!StringUtils.isEmpty(data)) {
            request.setBody(data, ContentType.APPLICATION_JSON);
        }
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), request::addHeader);
        }
        this.exec(request, commonsNetCall);
    }

    @Override
    public void post(String url, CommonsNetCall commonsNetCall, Map<String, String> formdata) {
        post(url, null, commonsNetCall, formdata);
    }

    @Override
    public void post(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> formdata) {
        SimpleHttpRequest request = SimpleHttpRequests.post(url);
        if (!CollectionUtils.isEmpty(formdata)) {
            List<NameValuePair> requestData = new ArrayList<>();
            MapUtils.forEach(formdata, (k, v) ->
                    requestData.add(new BasicNameValuePair(k, v)));
            // https://github.com/ok2c/httpclient-migration-guide/blob/master/samples/src/main/java/com/ok2c/httpcomponents/sample/HttpClient5AsyncSimpleExample.java
            ObjectMapper objectMapper = getObjectMapper();
            try {
                request.setBody(objectMapper.writeValueAsString(requestData), ContentType.APPLICATION_JSON);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                throw new CommonHttpException("formdata to json string error", e);
            }

        }
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), request::addHeader);
        }
        exec(request, commonsNetCall);
    }

    private ObjectMapper getObjectMapper() {
        if (null == this.objectMapper) {
            this.objectMapper = new ObjectMapper();
        }
        return this.objectMapper;
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

    private void exec(SimpleHttpRequest request, CommonsNetCall call) {
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
        this.httpClient.start();
        this.httpClient.execute(
                request,
                new FutureCallback<SimpleHttpResponse>() {
                    @SneakyThrows
                    @Override
                    public void completed(SimpleHttpResponse simpleHttpResponse) {
                        call.success(simpleHttpResponse);
                    }

                    @Override
                    public void failed(Exception e) {
                        call.file(e);
                    }

                    @Override
                    public void cancelled() {

                    }
                });
    }
}
