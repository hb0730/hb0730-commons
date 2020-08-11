<h1 align="center"><a href="https://github.com/halo-dev" target="_blank">hb0730-commons</a></h1>

<p align="center">
<a href="https://search.maven.org/search?q=com.hb0730">
<img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.hb0730/commons-parent?style=flat-square">
</a>

<a href="https://github.com/hb0730/hb0730-commons/blob/master/LICENSE">
<img alt="GitHub" src="https://img.shields.io/github/license/hb0730/hb0730-commons?style=flat-square">
</a>

<a href="https://travis-ci.com/github/hb0730/hb0730-commons">
<img alt="Travis (.com)" src="https://img.shields.io/travis/com/hb0730/hb0730-commons?style=flat-square">
</a>

<a href="https://www.oracle.com/java/technologies/javase-downloads.html">
<img alt="jdk" src="https://img.shields.io/badge/jdk-8%2B-green">
</a>

<a href=" https://apidoc.gitee.com/hb0820/hb0730-commons">
<img alt="api" src="https://img.shields.io/badge/api-doc-blue">
</a>
</p>

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
