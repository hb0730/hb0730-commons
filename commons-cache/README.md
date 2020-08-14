# Commons cache
java cache
## maven 依赖
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-cache</artifactId>
    <version>${commons.version}</version>
</dependency>
```
## 支持
+ MemoryCache 本地缓存
+ SpringData Redis 缓存
+ Jedis缓存

## 用法
## 本地缓存
```java
Cache<String, String> cache = new InMemoryCacheStore<>();
cache.put("test", "test");
cache.get("test")
```
具体用法可以参考`commons-cache-test`案例
