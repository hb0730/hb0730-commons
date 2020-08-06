package com.hb0730.commons.http.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.commons.http.CommonHttps;
import com.hb0730.commons.http.config.HttpConfig;
import com.hb0730.commons.http.support.callback.CommonsNetCall;
import com.hb0730.commons.http.support.httpclient.HttpClientAsyncImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3AsyncImpl;
import com.hb0730.commons.http.support.okhttp3.OkHttp3SyncImpl;
import com.hb0730.commons.http.utils.HttpAsyncUtils;
import com.hb0730.commons.http.utils.HttpSyncUtils;
import org.apache.hc.client5.http.async.methods.SimpleHttpResponse;
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
public class CommHttpUtilsTest {
    @Test
    public void httpSyncTest() {

    }

    @Test
    public void httpAsyncTest() {

    }

    @Test
    public void httpClientTest() throws InterruptedException, JsonProcessingException {
//        HttpSyncUtils syncUtils = CommonHttps.sync().setHttp(new HttpClientSyncImpl());
//        HttpClientTest.getSyncTest(syncUtils);
//        HttpClientTest.getSyncParamsTest(syncUtils);
//        HttpClientTest.postSyncTest(syncUtils);
//        HttpClientTest.postSyncParamsTest(syncUtils);
//        HttpClientTest.postSyncParamsJsonTest(syncUtils);
        HttpAsyncUtils asyncUtils = CommonHttps.async().setHttp(new HttpClientAsyncImpl());
//        HttpClientTest.getAsyncTest(asyncUtils);
//        HttpClientTest.getAsyncParamsTest(asyncUtils);
//        HttpClientTest.postAsyncTest(asyncUtils);
//        HttpClientTest.postAsyncParamsTest(asyncUtils);
        HttpClientTest.postAsyncParamsJsonTest(asyncUtils);

        Thread.sleep(TimeUnit.SECONDS.toMillis(2));

    }

    @Test
    public void okHttpTest() {
        HttpSyncUtils syncUtils = CommonHttps.sync()
                .setHttp(new OkHttp3SyncImpl());
        OkhttpTest.getSyncTest(syncUtils);
        OkhttpTest.getSyncParamsTest(syncUtils);
        OkhttpTest.postSyncTest(syncUtils);
        OkhttpTest.postSyncParamsTest(syncUtils);
        HttpAsyncUtils asyncUtils = CommonHttps.async().setHttp(new OkHttp3AsyncImpl());
        OkhttpTest.getAsyncTest(asyncUtils);
        OkhttpTest.getAsyncParamsTest(asyncUtils);
        OkhttpTest.postAsyncTest(asyncUtils);
        OkhttpTest.postAsyncParamsTest(asyncUtils);
    }

    public static class HttpClientTest {
        public static void getSyncTest(HttpSyncUtils utils) {
            System.out.println(utils.get("http://localhost:10000/"));
        }

        public static void getSyncParamsTest(HttpSyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            System.out.println(utils.get("http://localhost:10000/params", params));
        }

        public static void postSyncTest(HttpSyncUtils utils) {
            System.out.println(utils.post("http://localhost:10000/post"));
        }

        public static void postSyncParamsTest(HttpSyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            System.out.println(utils.post("http://localhost:10000/post/params", params));
        }

        public static void postSyncParamsJsonTest(HttpSyncUtils utils) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();
            params.put("id", "1");
            params.put("name", "测试");
            String json = mapper.writeValueAsString(params);
            System.out.println(utils.post("http://localhost:10000/post/params/json", json));
//            params.put("clientPhone", "13762716063");
//            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
//            String json = mapper.writeValueAsString(params);
//            System.out.println(utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", json));
        }

        public static void getAsyncTest(HttpAsyncUtils utils) {
            utils.get("http://localhost:10000/", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) {
                    if (response instanceof SimpleHttpResponse) {
                        System.out.println(((SimpleHttpResponse) response).getBodyText());
                    }
                }

                @Override
                public void file(Exception e) {

                }
            });
        }

        public static void getAsyncParamsTest(HttpAsyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈as哈哈");
            utils.setHttpConfig(HttpConfig.builder().encode(true).build());
            utils.get("http://localhost:10000/params", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) {
                    for (Object o : obj) {
                        System.out.println(o);
                    }
                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            }, params);
//            params.put("name", "手机");
//            utils.setHttpConfig(HttpConfig.builder().encode(true).build());
//            utils.get("http://localhost:8088/wx/renewal/category", new CommonsNetCall() {
//                @Override
//                public <T> void success(T response, Object... obj) throws IOException {
//                    for (Object o : obj) {
//                        System.out.println(o);
//                    }
//                }
//
//                @Override
//                public void file(Exception e) {
//                    e.printStackTrace();
//                }
//            }, params);

        }

        public static void postAsyncTest(HttpAsyncUtils utils) {
            utils.post("http://localhost:10000/post", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {
                    for (Object o : obj) {
                        System.out.println(o);
                    }
                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });
        }

        public static void postAsyncParamsTest(HttpAsyncUtils utils) throws JsonProcessingException {
            Map<String, String> params = new HashMap<>();
            params.put("name", "哈哈哈");
            utils.post("http://localhost:10000/post/params", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {
                    for (Object o : obj) {
                        System.out.println(o);
                    }
                }

                @Override
                public void file(Exception e) {

                }
            }, params);

//            Map<String, String> params = new HashMap<>();
//            params.put("clientPhone", "13762716063");
//            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
//            HttpHeader httpHeader = new HttpHeader();
//            httpHeader.add(Constants.CONTENT_TYPE, Constants.CONTENT_TYPE_JSON);
//            utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", httpHeader, new CommonsNetCall() {
//                @Override
//                public <T> void success(T response, Object... obj) throws IOException {
//                    for (Object o : obj) {
//                        System.out.println(o);
//                    }
//                }
//
//                @Override
//                public void file(Exception e) {
//                    e.printStackTrace();
//                }
//            }, params);

        }

        public static void postAsyncParamsJsonTest(HttpAsyncUtils utils) throws JsonProcessingException {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> params = new HashMap<>();
//            params.put("id", "1");
//            params.put("name", "车擦擦擦");
//            String json = mapper.writeValueAsString(params);
//            utils.post("http://localhost:10000/post/params/json", json, new CommonsNetCall() {
//                @Override
//                public <T> void success(T response, Object... obj) throws IOException {
//                    for (Object o : obj) {
//                        System.out.println(o);
//                    }
//                }
//
//                @Override
//                public void file(Exception e) {
//                    e.printStackTrace();
//                }
//            });

//            params.put("site_id", "2");
//            String json = mapper.writeValueAsString(params);
//            utils.post("http://api.bejson.com/Bejson/Api/LanguageGroup/getGroupList", json, new CommonsNetCall() {
//                @Override
//                public <T> void success(T response, Object... obj) throws IOException {
//                    for (Object o : obj) {
//                        System.out.println(o);
//                    }
//                }
//
//                @Override
//                public void file(Exception e) {
//                    e.printStackTrace();
//                }
//            });

            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            String json = mapper.writeValueAsString(params);
            utils.post("http://localhost:8088/wx/renewal/order/selectRenewalOrder", json, new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {
                    for (Object o : obj) {
                        System.out.println(o);
                    }
                }

                @Override
                public void file(Exception e) {
                    e.printStackTrace();
                }
            });

        }
    }

    public static class OkhttpTest {
        public static void getSyncTest(HttpSyncUtils utils) {
            System.out.println(utils.get("https://baidu.com"));
        }

        public static void getSyncParamsTest(HttpSyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "手机");
            System.out.println(utils.get("http://localhost/", params));
        }

        public static void postSyncTest(HttpSyncUtils utils) {
            System.out.println(utils.post("https://baidu.com"));
        }

        public static void postSyncParamsTest(HttpSyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            System.out.println(utils.post("https://fixtest.hohofast.com/recovery/wx/renewal/order/selectRenewalOrder",
                    params));
        }

        public static void getAsyncTest(HttpAsyncUtils utils) {
            utils.get("https://baidu.com", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            });
        }

        public static void getAsyncParamsTest(HttpAsyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("name", "手机");
            utils.get("https://baidu.com", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            }, params);
        }

        public static void postAsyncTest(HttpAsyncUtils utils) {
            utils.post("https://baidu.com", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            });
        }

        public static void postAsyncParamsTest(HttpAsyncUtils utils) {
            Map<String, String> params = new HashMap<>();
            params.put("clientPhone", "13762716063");
            params.put("openId", "oD5gh5Q59CWc8w7jRQ9hNa9_kJZ0");
            utils.post("https://baidu.com", new CommonsNetCall() {
                @Override
                public <T> void success(T response, Object... obj) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            }, params);
        }
    }
}
