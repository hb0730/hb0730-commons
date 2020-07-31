package com.hb0730.commons.http.support.okhttp3;

import com.hb0730.commons.http.AbstractHttp;
import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.lang.MapUtils;
import com.hb0730.commons.lang.StringUtils;
import okhttp3.*;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.Objects;

/**
 * okhttp3 impl
 *
 * @author bing_huang
 * @date 2020/07/30 16:45
 * @since V1.0
 */
public class OkHttp3Impl extends AbstractHttp {
    private final okhttp3.OkHttpClient.Builder clientBuilder;

    private static final MediaType JSON = MediaType.parse(Constants.CONTENT_TYPE_JSON);

    public OkHttp3Impl() {
        this(new HttpConfig());
    }

    public OkHttp3Impl(HttpConfig config) {
        this(new OkHttpClient().newBuilder(), config);
    }

    public OkHttp3Impl(okhttp3.OkHttpClient.Builder clientBuilder, HttpConfig config) {
        super(config);
        this.clientBuilder = clientBuilder;

    }

    @Override
    public String get(String url) {
        return this.get(url, null, null);
    }

    @Override
    public String get(String url, Map<String, String> params) {
        return this.get(url, null, params);
    }

    @Override
    public String get(String url, HttpHeader header, Map<String, String> params) {
        if (StringUtils.isEmpty(url)) {
            return "";
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
        return exec(builder);
    }

    @Override
    public String post(String url) {
        return this.post(url, "", null);
    }

    @Override
    public String post(String url, String data) {
        return this.post(url, data, null);
    }

    @Override
    public String post(String url, String data, HttpHeader header) {
        if (StringUtils.isEmpty(url)) {
            return "";
        }
        Request.Builder requestBuilder = new Request.Builder().url(url);
        if (null != header) {
            MapUtils.forEach(header.getHeaders(), requestBuilder::addHeader);
        }
        RequestBody body = RequestBody.create(data, JSON);
        requestBuilder.post(body);
        return exec(requestBuilder);
    }

    @Override
    public String post(String url, Map<String, String> formdata) {
        return this.post(url, null, formdata);
    }

    @Override
    public String post(String url, HttpHeader header, Map<String, String> formdata) {
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
        return exec(requestBuilder);
    }

    public String exec(Request.Builder requestBuilder) {
        String result = "";
        if (null == requestBuilder) {
            return "";
        }
        this.addHeader(requestBuilder);

        Request request = requestBuilder.build();

        OkHttpClient client = null;
        OkHttpClient.Builder builder = clientBuilder.connectTimeout(Duration.ofMillis(httpConfig.getTimeout()))
                .readTimeout(Duration.ofMillis(httpConfig.getTimeout()))
                .writeTimeout(Duration.ofMillis(httpConfig.getTimeout()));
        if (null != httpConfig.getProxy()) {
            client = builder.proxy(httpConfig.getProxy()).build();

        } else {
            client = builder.build();
        }
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;

    }

    private void addHeader(Request.Builder builder) {
        builder.header(Constants.USER_AGENT, Constants.USER_AGENT_DATA);
    }
}
