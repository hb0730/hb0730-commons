# commons json
针对市面已有(jackson,gson,fastjson...)的做封装
## Maven
```xml
<dependency>
<groupId>com.hb0730</groupId>
<artifactId>commons-json</artifactId>
<version>${version}</version>
</dependency>
```

## 用法
## jackson
依赖
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-json</artifactId>
    <version>${version}</version>
</dependency>
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>${jackson.version}</version>
</dependency>
```
使用

```java
String json = "{\"id\":\"1\",\"name\":\"测试\"}";
Jsons jsons = Jsons.JSONS;
Test1 test1 = jsons.jsonToObject(json, Test1.class);
```
或者
```java
String json = "{\"id\":\"1\",\"name\":\"测试\"}";
Jsons jsons = Jsons.JSONS;
Test1 test1 = jsons.jsonToObject(json, Test1.class, new ObjectMapper());
```

## 特点
+ 默认按照maven依赖查找`fastjson -> gson -> jackson`
+ 当有多个依赖是，可以自行设置使用哪个json:`setJson(Class<? extends AbstractJson> support) `或者`setJson(AbstractJson support)`
