package com.hb0730.commons.http.support.httpclient;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.support.callback.HttpCallback;
import com.hb0730.commons.lang.map.MapBuilder;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Map;

public class HttpClientAsyncImplTest {

    @After
    public void after() throws InterruptedException {

        Thread.sleep(200L);
    }

    @Test
    public void httpClientAsyncImplTest() {
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
        Assert.assertNotNull("创建失败", async);
    }

    @Test
    public void testHttpClientAsyncImplTest() {
        HttpClientAsyncImpl async = new HttpClientAsyncImpl(HttpConfig.builder().build());
        Assert.assertNotNull("创建失败", async);

    }

    @Test
    public void testHttpClientAsyncImpl2Test() {
        CloseableHttpAsyncClient client = HttpAsyncClients.createDefault();
        HttpClientAsyncImpl async = new HttpClientAsyncImpl(client, HttpConfig.builder().build());
        Assert.assertNotNull("创建失败", async);
    }

    @Test
    public void getTest() {
        // get请求
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
        async.get("http://poetry.apiopen.top/getTime", params, new HttpCallback() {
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
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

    @Test
    public void getHttpClientTest() throws IOException {
        HttpClientAsyncImpl async = new HttpClientAsyncImpl();
        // 需要关闭
        CloseableHttpAsyncClient httpClient = async.getHttpClient();
        httpClient.close();
    }
}
