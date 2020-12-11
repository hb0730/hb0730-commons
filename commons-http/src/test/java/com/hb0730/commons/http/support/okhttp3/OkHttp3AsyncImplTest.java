package com.hb0730.commons.http.support.okhttp3;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.inter.AbstractAsyncHttp;
import com.hb0730.commons.http.inter.AsyncHttp;
import com.hb0730.commons.http.support.callback.HttpCallback;
import com.hb0730.commons.lang.map.MapBuilder;
import okhttp3.OkHttpClient;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class OkHttp3AsyncImplTest {
    @After
    public void after() throws InterruptedException {

        Thread.sleep(200L);
    }

    @Test
    public void okHttp3AsyncImplTest() {
        AsyncHttp async = new OkHttp3AsyncImpl();
        Assert.assertNotNull("创建失败", async);
    }

    @Test
    public void testOkHttp3AsyncImplTest() {
        AbstractAsyncHttp async = new OkHttp3AsyncImpl(HttpConfig.builder().build());
        Assert.assertNotNull("创建失败", async);
    }

    @Test
    public void testOkHttp3AsyncImplTest2() {
        OkHttp3AsyncImpl async = new OkHttp3AsyncImpl(new OkHttpClient.Builder(), HttpConfig.builder().build());
        Assert.assertNotNull("创建失败", async);
    }

    @Test
    public void getTest() {
        // get请求
        AsyncHttp async = new OkHttp3AsyncImpl();
        //http://poetry.apiopen.top/getTime
        async.get("", new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testGetTest() {
        //有参请求
        MapBuilder<String, String> build = MapBuilder.build();
        Map<String, String> params = build.put("count", "2").builder();
        AsyncHttp async = new OkHttp3AsyncImpl();
        //http://poetry.apiopen.top/getTime
        async.get("", params, new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testGet1Test() {
        //多参请求
        MapBuilder<String, String> build = MapBuilder.build();
        Map<String, String> params = build.put("count", "2").builder();
        //http://poetry.apiopen.top/poetryFull?page=1
        AsyncHttp async = new OkHttp3AsyncImpl();
        async.get("", params, new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testGet2Test() {
        //请求头 参数判断
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
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.setHeader(httpHeader);
        async.get("", params, new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testGet3Test() {
        //请求头 参数判断
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
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.setHttpConfig(HttpConfig.builder().encode(true).build());
        async.setHeader(httpHeader);
        async.get("", params, new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void postTest() {
        //http://meiriyikan.cn/api/json.php
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.post("", new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testPostTest() {
        String json = "{\"name:\",\"李白\"}";
        // https://api.apiopen.top/likePoetry
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.post("", json, new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testPost1Test() {
        MapBuilder<String, String> mapBuilder = MapBuilder.build();
        mapBuilder.put("type", "all");
        mapBuilder.put("page", "1");
        mapBuilder.put("count", "10");
        // https://api.apiopen.top/getJoke
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.post("", mapBuilder.builder(), new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testPost2Test() {
        String json = "{\"name:\",\"李白\"}";
        // https://api.apiopen.top/likePoetry
        HttpHeader header = HttpHeader.builder().add("Accept", "*/*");
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.setHeader(header);
        async.post("", json, new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

    @Test
    public void testPost3Test() {
        MapBuilder<String, String> mapBuilder = MapBuilder.build();
        mapBuilder.put("type", "all");
        mapBuilder.put("page", "1");
        mapBuilder.put("count", "10");
        // https://api.apiopen.top/getJoke
        HttpHeader header = HttpHeader.builder().add("Accept", "*/*");
        AbstractAsyncHttp async = new OkHttp3AsyncImpl();
        async.setHeader(header);
        async.post("", mapBuilder.builder(), new HttpCallback() {
            @Override
            public void success(String result) throws IOException {
                Assert.assertNotNull("获取失败", result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {
                throw new RuntimeException("请求失败", e);
            }
        });
    }

}
