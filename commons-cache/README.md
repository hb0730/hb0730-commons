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

## jackson泛型擦除问题
```java
  @Test
public void javaTypeTest() {
        CacheWrapper<List<DataClass<String>>> testData = new CacheWrapper<>();
        List<DataClass<String>> listData = Lists.newArrayList();
        listData.add(new DataClass<>("测试1"));
        listData.add(new DataClass<>("测试2"));
        listData.add(new DataClass<>("测试3"));
        testData.setData(listData);
        JavaType javaType = TypeFactory.defaultInstance().constructParametricType(DataClass.class, String.class);
        JavaType javaType1 = TypeFactory.defaultInstance().constructParametricType(List.class, javaType);
        Serializer<List<DataClass<String>>> serializer = new Jackson2JsonCacheWrapperSerializer<>(true, javaType1);
        byte[] serialize = serializer.serialize(testData);
        CacheWrapper<List<DataClass<String>>> deserialize = serializer.deserialize(serialize);
        List<DataClass<String>> data = deserialize.getData();
        Assert.assertNotNull("序列化失败", data);
        }

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public static class DataClass<T> implements Serializable {
    private T data;
}
```
