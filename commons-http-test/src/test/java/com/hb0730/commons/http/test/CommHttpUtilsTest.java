package com.hb0730.commons.http.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.Https;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.constants.Constants;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl;
import com.hb0730.commons.http.utils.HttpAsync;
import com.hb0730.commons.http.utils.HttpSync;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author bing_huang
 * @date 2020/08/05 16:22
 * @since V1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = HttpTestApplication.class)
@ActiveProfiles
@Slf4j
public class CommHttpUtilsTest {
    @Test
    public void httpSyncTest() {

    }

    @Test
    public void httpAsyncTest() {

    }

    // 未关闭client
    @Test
    public void httpClientTest() throws InterruptedException, JsonProcessingException {
//        HttpSyncUtils syncUtils = CommonHttps.sync().setHttp(new HttpClientSyncImpl());
//        HttpClientTest.getSyncTest(syncUtils);
//        HttpClientTest.getSyncParamsTest(syncUtils);
//        HttpClientTest.postSyncTest(syncUtils);
//        HttpClientTest.postSyncParamsTest(syncUtils);
//        HttpClientTest.postSyncParamsJsonTest(syncUtils);
        HttpAsync asyncUtils = ((HttpAsync) Https.ASYNC.getHttp()).setHttp(new HttpClientAsyncImpl());
//        HttpClientTest.getAsyncTest(asyncUtils);
//        HttpClientTest.getAsyncParamsTest(asyncUtils);
//        HttpClientTest.postAsyncTest(asyncUtils);
//        HttpClientTest.postAsyncParamsTest(asyncUtils);
        HttpClientTest.postAsyncParamsJsonTest(asyncUtils);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));

    }

    @Test
    public void okHttpTest() throws JsonProcessingException, InterruptedException {
//        HttpSyncUtils syncUtils = CommonHttps.sync().setHttp(new OkHttp3SyncImpl());
//        OkhttpTest.getSyncTest(syncUtils);
//        OkhttpTest.getSyncParamsTest(syncUtils);
//        OkhttpTest.postSyncTest(syncUtils);
//        OkhttpTest.postSyncParamsTest(syncUtils);
//        OkhttpTest.postSyncParamsJsonTest(syncUtils);
        HttpAsync asyncUtils = ((HttpAsync) Https.ASYNC.getHttp()).setHttp(new OkHttp3AsyncImpl());
//        OkhttpTest.getAsyncTest(asyncUtils);
//        OkhttpTest.getAsyncParamsTest(asyncUtils);
//        OkhttpTest.postAsyncTest(asyncUtils);
//        OkhttpTest.postAsyncParamsTest(asyncUtils);

        OkhttpTest.postAsyncParamsJsonTest(asyncUtils);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));
    }

    public static class HttpClientTest {
        public static void getSyncTest(HttpSync utils) {
            System.out.println(utils.get("http://localhost:10000/"));
        }

        public static void getSyncParamsTest(HttpSync utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            log.info(utils.get("http://localhost:10000/params", params));
        }

        public static void postSyncTest(HttpSync utils) {
            log.info(utils.post("http://localhost:10000/post"));
        }

        public static void postSyncParamsTest(HttpSync utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            log.info(utils.post("http://localhost:10000/post/params", params));
        }

        public static void postSyncParamsJsonTest(HttpSync utils) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();
            params.put("id", "1");
            params.put("name", "测试");
            String json = mapper.writeValueAsString(params);
            log.info(utils.post("http://localhost:10000/post/params/json", json));

            params.clear();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            json = mapper.writeValueAsString(params);
            log.info(utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", json));
        }

        public static void getAsyncTest(HttpAsync utils) {
            utils.get("http://localhost:10000/", new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            });
        }

        public static void getAsyncParamsTest(HttpAsync utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈as哈哈");
            utils.setHttpConfig(HttpConfig.builder().encode(true).build());
            utils.get("http://localhost:10000/params", new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);

            params.clear();
            params.put("name", "手机");
            utils.setHttpConfig(HttpConfig.builder().encode(true).build());
            utils.get("http://localhost:8088/wx/renewal/category", new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);

        }

        public static void postAsyncTest(HttpAsync utils) {
            utils.post("http://localhost:10000/post", new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });
        }

        public static void postAsyncParamsTest(HttpAsync utils) throws JsonProcessingException {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            utils.post("http://localhost:10000/post/params", new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            }, params);

            params.clear();
            params = new HashMap<>();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            HttpHeader httpHeader = new HttpHeader();
            httpHeader.add(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
            utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", httpHeader, new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);

        }

        public static void postAsyncParamsJsonTest(HttpAsync utils) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();

            params.put("id", "1");
            params.put("name", "车擦擦擦");
            String json = mapper.writeValueAsString(params);
            utils.post("http://localhost:10000/post/params/json", json, new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

            params.clear();
            params.put("site_id", "2");
            json = mapper.writeValueAsString(params);
            utils.post("http://api.bejson.com/Bejson/Api/LanguageGroup/getGroupList", json, new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

            params.clear();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            json = mapper.writeValueAsString(params);
            utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", json, new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

        }
    }

    public static class OkhttpTest {
        public static void getSyncTest(HttpSync utils) {
            System.out.println(utils.get("http://localhost:10000/"));
        }

        public static void getSyncParamsTest(HttpSync utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            log.info(utils.get("http://localhost:10000/params", params));
        }

        public static void postSyncTest(HttpSync utils) {
            log.info(utils.post("http://localhost:10000/post"));
        }

        public static void postSyncParamsTest(HttpSync utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            log.info(utils.post("http://localhost:10000/post/params", params));
        }

        public static void postSyncParamsJsonTest(HttpSync utils) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();

            params.put("id", "1");
            params.put("name", "测试");
            String json = mapper.writeValueAsString(params);
            log.info(utils.post("http://localhost:10000/post/params/json", json));

            params.clear();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            json = mapper.writeValueAsString(params);
            log.info(utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", json));
        }

        public static void getAsyncTest(HttpAsync utils) {
            utils.get("http://localhost:10000/", new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            });
        }

        public static void getAsyncParamsTest(HttpAsync utils) {
            Map<String, String> params = new HashMap<>();

            params.put("name", "哈as哈哈");
            utils.setHttpConfig(HttpConfig.builder().encode(true).build());
            utils.get("http://localhost:10000/params", new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);

            params.clear();
            params.put("name", "手机");
            utils.setHttpConfig(HttpConfig.builder().encode(true).build());
            utils.get("http://localhost:8088/wx/renewal/category", new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);

        }

        public static void postAsyncTest(HttpAsync utils) {
            utils.post("http://localhost:10000/post", new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });
        }

        public static void postAsyncParamsTest(HttpAsync utils) {
            Map<String, String> params = new HashMap<>();

            params.put("name", "哈哈哈");
            utils.post("http://localhost:10000/post/params", new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            }, params);

            params.clear();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            HttpHeader httpHeader = new HttpHeader();
            httpHeader.add(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
            utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", httpHeader, new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);
        }

        public static void postAsyncParamsJsonTest(HttpAsync utils) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();

            params.put("id", "1");
            params.put("name", "车擦擦擦");
            String json = mapper.writeValueAsString(params);
            utils.post("http://localhost:10000/post/params/json", json, new CommonsNetCall() {
                @Override
                public void success(String result) throws IOException {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

            params.clear();
            params.put("site_id", "2");
            json = mapper.writeValueAsString(params);
            utils.post("http://api.bejson.com/Bejson/Api/LanguageGroup/getGroupList", json, new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

            params.clear();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            json = mapper.writeValueAsString(params);
            utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", json, new CommonsNetCall() {
                @Override
                public void success(String result) {
                    log.info(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

        }
    }
}
