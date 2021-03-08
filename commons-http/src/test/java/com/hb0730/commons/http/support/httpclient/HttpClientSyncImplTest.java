package com.hb0730.commons.http.support.httpclient;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.lang.map.MapBuilder;
import org.apache.hc.core5.util.Timeout;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class HttpClientSyncImplTest {

    @Test
    public void getHttpConfigTest() {
        //创建
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        //http://poetry.apiopen.top/getTime
        String result = sync.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void setHttpConfigTest() {
        //根据client创建
        CloseableHttpClient client = HttpClients.createDefault();
        HttpClientSyncImpl sync = new HttpClientSyncImpl(client);
        //http://poetry.apiopen.top/getTime
        String result = sync.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testSetHttpConfigTest() {
        //根据client与header创建
        CloseableHttpClient client = HttpClients.createDefault();
        HttpClientSyncImpl sync = new HttpClientSyncImpl(HttpConfig.builder().timeout(Timeout.ofMinutes(1).toMilliseconds()).build(), client);
        //http://poetry.apiopen.top/getTime
        String result = sync.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void getTest() {
        //无参请求
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        //http://poetry.apiopen.top/getTime
        String result = sync.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testGetTest() {
        //有参请求
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        MapBuilder<String, String> build = MapBuilder.builder();
        Map<String, String> params = build.put("count", "2").build();
        //http://poetry.apiopen.top/getTime
        String result = sync.get("", params);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testGet2Test() {
        //多参请求
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        MapBuilder<String, String> build = MapBuilder.builder();
        Map<String, String> params = build.put("count", "2").build();
        //http://poetry.apiopen.top/poetryFull?page=1
        String result = sync.get("", params);
        Assert.assertNotNull("获取失败", result);
    }


    @Test
    public void testGet1Test() {
        //请求头 参数判断
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        MapBuilder<String, String> build = MapBuilder.builder();
        Map<String, String> params = build
                .put("count", "2")
                .put("page", "1")
                .put("name", "李白")
                .build();
        HttpHeader httpHeader = HttpHeader.builder();
        httpHeader.add("Accept", "*/*");
        //测试为空,为null
        httpHeader.add("ab", "");
        httpHeader.add("ac", null);
        //http://poetry.apiopen.top/poetryAuthor
        sync.setHeader(httpHeader);
        String result = sync.get("", params);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testGet3Test() {
        //请求头 参数判断
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        sync.setHttpConfig(HttpConfig.builder().encode(true).build());
        MapBuilder<String, String> build = MapBuilder.builder();
        Map<String, String> params = build
                .put("count", "2")
                .put("page", "1")
                .put("name", "李白")
                .build();
        HttpHeader httpHeader = HttpHeader.builder();
        httpHeader.add("Accept", "*/*");
        //测试为空,为null
        httpHeader.add("ab", "");
        httpHeader.add("ac", null);
        //http://poetry.apiopen.top/poetryAuthor
        sync.setHeader(httpHeader);
        String result = sync.get("", params);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void postTest() {
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        //http://meiriyikan.cn/api/json.php
        String result = sync.post("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPostTest() {
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        String json = "{\"name:\",\"李白\"}";
        // https://api.apiopen.top/likePoetry
        String result = sync.post("", json);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPost1Test() {
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        MapBuilder<String, String> mapBuilder = MapBuilder.builder();
        mapBuilder.put("type", "all");
        mapBuilder.put("page", "1");
        mapBuilder.put("count", "10");
        // https://api.apiopen.top/getJoke
        String result = sync.post("", mapBuilder.build());
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPost2Test() {
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        String json = "{\"name:\",\"李白\"}";
        // https://api.apiopen.top/likePoetry
        HttpHeader header = HttpHeader.builder().add("Accept", "*/*");
        sync.setHeader(header);
        String result = sync.post("", json);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPost3Test() {
        HttpClientSyncImpl sync = new HttpClientSyncImpl();
        MapBuilder<String, String> mapBuilder = MapBuilder.builder();
        mapBuilder.put("type", "all");
        mapBuilder.put("page", "1");
        mapBuilder.put("count", "10");
        // https://api.apiopen.top/getJoke
        HttpHeader header = HttpHeader.builder().add("Accept", "*/*");
        sync.setHeader(header);
        String result = sync.post("", mapBuilder.build());
        Assert.assertNotNull("获取失败", result);
    }
}
