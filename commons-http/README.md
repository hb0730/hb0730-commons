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
 HttpSync http = Https.SYNC.getHttp();
 String s = http.get("https://baidu.com");

  s = http.setHttp(new OkHttp3SyncImpl())
                 .get("https://baidu.com");
```
或者
```java
HttpAsync http = Https.ASYNC.getHttp();
        http.get("http://baidu.com", new CommonsNetCall() {
            @Override
            public void success(String result) throws IOException {
                log.debug(result);
            }

            @Override
            public void success(byte[] result) throws IOException {

            }

            @Override
            public void file(Exception e) {

            }
        });
```
具体请参考`commons-http-test` 案例或者javadoc
