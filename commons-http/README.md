# Commons Http 
commons-http 扩展自[simple-http-1.0.2](https://github.com/xkcoding/simple-http)
## maven依赖
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-http</artifactId>
    <version>${commons.version}</version>
</dependency>
```
## commons http支持
+ Apache httpClient Sync与Async
+ okHttpClient Sync和Async

## 用法
```java
HttpSyncUtils syncUtils = CommonHttps.sync().setHttp(new HttpClientSyncImpl());
utils.get("http://localhost:10000/")
```
或者
```java
HttpAsyncUtils asyncUtils = CommonHttps.async().setHttp(new HttpClientAsyncImpl());
utils.get("http://localhost:10000/", new CommonsNetCall() {
                @Override
                public void success(String result) {
                    System.out.println(result);
                }

                @Override
                public void success(byte[] result) throws IOException {

                }

                @Override
                public void file(Exception e) {

                }
            });
```
具体请参考`commons-http-test`案例
