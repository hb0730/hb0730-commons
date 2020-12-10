package com.hb0730.commons.http.support.okhttp3;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.exception.CommonHttpException;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.map.MapUtils;
import okhttp3.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

/**
 * okhttp3 async impl
 *
 * @author bing_huang
 * @since 1.0.1
 */
public class OkHttp3AsyncImpl extends AbstractAsyncHttp {
    private final okhttp3.OkHttpClient.Builder clientBuilder;

    private static final MediaType JSON = MediaType.parse(Constants.CONTENT_TYPE_JSON);

    public OkHttp3AsyncImpl() {
        this(new HttpConfig());
    }

    public OkHttp3AsyncImpl(HttpConfig config) {
        this(new OkHttpClient().newBuilder(), config);
    }

    public OkHttp3AsyncImpl(okhttp3.OkHttpClient.Builder clientBuilder, HttpConfig config) {
        super(config);
        this.clientBuilder = clientBuilder;
    }

    @Override
    public void get(String url, CommonsNetCall commonsNetCall) {
        this.get(url, commonsNetCall, null);
    }

    @Override
    public void get(String url, CommonsNetCall commonsNetCall, Map<String, String> params) {
        this.get(url, null, commonsNetCall, params);
    }

    @Override
    public void get(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> params) {
        if (StringUtils.isBlank(url)) {
            throw new CommonHttpException("request url must be not null");
        }
        HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
        if (this.httpConfig.isEncode()) {
            MapUtils.forEach(params, urlBuilder::addEncodedQueryParameter);
        } else {
            MapUtils.forEach(params, urlBuilder::addQueryParameter);
        }
        HttpUrl httpUrl = urlBuilder.build();
        Request.Builder requestBuilder = new Request.Builder().url(httpUrl);
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), requestBuilder::addHeader);
        }
        Request.Builder builder = requestBuilder.get();
        exec(builder, commonsNetCall);
    }

    @Override
    public void post(String url, CommonsNetCall commonsNetCall) {
        this.post(url, "", null, commonsNetCall);
    }

    @Override
    public void post(String url, String dataJson, CommonsNetCall commonsNetCall) {
        this.post(url, dataJson, null, commonsNetCall);
    }

    @Override
    public void post(String url, String dataJson, HttpHeader header, CommonsNetCall commonsNetCall) {
        if (StringUtils.isBlank(url)) {
            throw new CommonHttpException("request url must be not null");
        }
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), requestBuilder::addHeader);
        }
        RequestBody body = RequestBody.create(dataJson, JSON);
        requestBuilder.post(body);
        exec(requestBuilder, commonsNetCall);
    }

    @Override
    public void post(String url, CommonsNetCall commonsNetCall, Map<String, String> formdata) {
        this.post(url, null, commonsNetCall, formdata);
    }

    @Override
    public void post(String url, HttpHeader header, CommonsNetCall commonsNetCall, Map<String, String> formdata) {
        if (StringUtils.isBlank(url)) {
            throw new CommonHttpException("request url must be not null");
        }
        FormBody.Builder builder = new FormBody.Builder();
        if (this.httpConfig.isEncode()) {
            MapUtils.forEach(formdata, builder::addEncoded);
        } else {
            MapUtils.forEach(formdata, builder::add);
        }
        FormBody body = builder.build();
        Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), requestBuilder::addHeader);
        }
        exec(requestBuilder, commonsNetCall);
    }

    private void addHeader(Request.Builder builder) {
        builder.header(Constants.USER_AGENT, Constants.USER_AGENT_DATA);
    }

    private void exec(Request.Builder requestBuilder, CommonsNetCall commonsNetCall) {
        this.addHeader(requestBuilder);
        Request request = requestBuilder.build();
        OkHttpClient client;
        OkHttpClient.Builder builder = clientBuilder.connectTimeout(Duration.ofMillis(httpConfig.getTimeout()))
                .readTimeout(Duration.ofMillis(httpConfig.getTimeout()))
                .writeTimeout(Duration.ofMillis(httpConfig.getTimeout()));
        if (null != httpConfig.getProxy()) {
            client = builder.proxy(httpConfig.getProxy()).build();

        } else {
            client = builder.build();
        }
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (null == commonsNetCall) {
                    return;
                }
                commonsNetCall.file(e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (null == commonsNetCall) {
                    return;
                }
                try (ResponseBody responseBody = response.body()) {
                    if (response.isSuccessful()) {
                        commonsNetCall.success(responseBody.string());
                    }
                }
            }
        });
    }
}
