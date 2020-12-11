package com.hb0730.commons.http.support.okhttp3;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.inter.AbstractSyncHttp;
import com.hb0730.commons.http.inter.SyncHttp;
import com.hb0730.commons.lang.map.MapBuilder;
import okhttp3.OkHttpClient;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

public class OkHttp3SyncImplTest {
    @Test
    public void okHttp3SyncTest() {
        OkHttp3SyncImpl sync = new OkHttp3SyncImpl();
        Assert.assertNotNull("创建失败", sync);
    }

    @Test
    public void testOkHttp3SyncTest() {
        OkHttp3SyncImpl sync = new OkHttp3SyncImpl(HttpConfig.builder().build());
        Assert.assertNotNull("创建失败", sync);
    }

    @Test
    public void testOkHttp3SyncTest2() {
        OkHttpClient.Builder newBuilder = new OkHttpClient().newBuilder();
        OkHttp3SyncImpl sync = new OkHttp3SyncImpl(newBuilder, HttpConfig.builder().build());
        Assert.assertNotNull("创建失败", sync);
    }

    @Test
    public void getTest() {
        //无参请求
        SyncHttp sync = new OkHttp3SyncImpl();
        //http://poetry.apiopen.top/getTime
        String result = sync.get("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testGetTest() {
        //有参请求
        SyncHttp sync = new OkHttp3SyncImpl();
        MapBuilder<String, String> build = MapBuilder.build();
        Map<String, String> params = build.put("count", "2").builder();
        //http://poetry.apiopen.top/getTime
        String result = sync.get("", params);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testGet2Test() {
        //多参请求
        SyncHttp sync = new OkHttp3SyncImpl();
        MapBuilder<String, String> build = MapBuilder.build();
        Map<String, String> params = build.put("count", "2").builder();
        //http://poetry.apiopen.top/poetryFull?page=1
        String result = sync.get("", params);
        Assert.assertNotNull("获取失败", result);
    }


    @Test
    public void testGet1Test() {
        //请求头 参数判断
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        MapBuilder<String, String> build = MapBuilder.build();
        Map<String, String> params = build
                .put("count", "2")
                .put("page", "1")
                .put("name", "李白")
                .builder();
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
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        sync.setHttpConfig(HttpConfig.builder().encode(true).build());
        MapBuilder<String, String> build = MapBuilder.build();
        Map<String, String> params = build
                .put("count", "2")
                .put("page", "1")
                .put("name", "李白")
                .builder();
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
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        //http://meiriyikan.cn/api/json.php
        String result = sync.post("");
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPostTest() {
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        String json = "{\"name:\",\"李白\"}";
        // https://api.apiopen.top/likePoetry
        String result = sync.post("", json);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPost1Test() {
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        MapBuilder<String, String> mapBuilder = MapBuilder.build();
        mapBuilder.put("type", "all");
        mapBuilder.put("page", "1");
        mapBuilder.put("count", "10");
        // https://api.apiopen.top/getJoke
        String result = sync.post("", mapBuilder.builder());
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPost2Test() {
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        String json = "{\"name:\",\"李白\"}";
        // https://api.apiopen.top/likePoetry
        HttpHeader header = HttpHeader.builder().add("Accept", "*/*");
        sync.setHeader(header);
        String result = sync.post("", json);
        Assert.assertNotNull("获取失败", result);
    }

    @Test
    public void testPost3Test() {
        AbstractSyncHttp sync = new OkHttp3SyncImpl();
        MapBuilder<String, String> mapBuilder = MapBuilder.build();
        mapBuilder.put("type", "all");
        mapBuilder.put("page", "1");
        mapBuilder.put("count", "10");
        // https://api.apiopen.top/getJoke
        HttpHeader header = HttpHeader.builder().add("Accept", "*/*");
        sync.setHeader(header);
        String result = sync.post("", mapBuilder.builder());
        Assert.assertNotNull("获取失败", result);
    }
}
