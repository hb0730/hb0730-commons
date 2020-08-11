# hb0730 Commons

-----------------------
## 简介
hb0730-commons是个人平时用到的java类库(封装http,cache或者工具utils)仅此而已
## 包含组件
|模块|介绍|
|----|----|
|commons-cache|封装的缓存工具,支持本地内存缓存以及spring data redis缓存|
|commons-http|简单抽象http，支持httpClient,okHttp等工具|
|commons-json|集合各大(fastjson,gson,jackson...)json工具类|
|commons-lang|平时用到的单一工具如String,Date等utils|
|commons-spring|spring相关工具|
## 测试 
测试依赖第三方组件

|模块|
|----|
|commons-cache-test|
|commons-http-test|
|commons-spring-test|
## 安装
### maven
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-dependencies</artifactId>
    <version>1.0.2-SNAPSHOT</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```
或者
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-all</artifactId>
    <version>1.0.2-SNAPSHOT</version>
</dependency>
```
