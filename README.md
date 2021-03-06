<h1 align="center"><a href="https://github.com/hb0730/hb0730-commons" target="_blank">commons</a></h1>

<p align="center">
<a href="https://search.maven.org/artifact/com.hb0730/commons-all">
<img alt="Maven Central" src="https://img.shields.io/maven-central/v/com.hb0730/commons-parent?style=flat-square">
</a>

<a href="https://github.com/hb0730/hb0730-commons/blob/master/LICENSE">
<img alt="GitHub" src="https://img.shields.io/github/license/hb0730/hb0730-commons?style=flat-square">
</a>

<a href="https://github.com/hb0730/hb0730-commons/actions">
<img alt="GitHub Workflow Status" src="https://img.shields.io/github/workflow/status/hb0730/hb0730-commons/commons%20CI?style=flat-square">
</a>

<a href="https://www.oracle.com/java/technologies/javase-downloads.html">
<img alt="jdk" src="https://img.shields.io/badge/jdk-8%2B-green?style=flat-square">
</a>

<a href="https://apidoc.gitee.com/hb0730/hb0730-commons">
<img alt="api" src="https://img.shields.io/badge/api-doc-blue?style=flat-square">
</a>

<a href="https://www.codacy.com/manual/hb0730/hb0730-commons?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=hb0730/hb0730-commons&amp;utm_campaign=Badge_Grade">
<img alt="codacy" src="https://app.codacy.com/project/badge/Grade/32651203ac1043cb8c85fddf42a68c9a"/>
</a>

<a href="https://codecov.io/gh/hb0730/hb0730-commons">
  <img alt="codecov" src="https://codecov.io/gh/hb0730/hb0730-commons/branch/dev/graph/badge.svg" />
</a>

</p>

-----------------------
## 简介
hb0730-commons是个人平时用到的java类库(封装http,cache等工具)
## 包含组件
|模块|介绍|
|----|----|
|commons-cache|封装的缓存工具,支持本地内存缓存以及spring data redis缓存|
|commons-http|简单抽象http，支持httpClient,okHttp等工具|
|commons-json|集合各大(fastjson,gson,jackson...)json工具类|
|comons-encrypt|加密解密工具|
|commons-lang|平时用到的单一工具如String,Date等utils|
|commons-mail|java发送mail使用spring Mail|
|commons-spring|spring相关工具|
|commons-ssh|对 [jsch](http://www.jcraft.com/jsch/) 等SSH Client简单封装|
|commons-scm|对 scm(git,svn)等支持,方便操作|
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
    <version>2.1.2-RELEASE</version>
    <type>pom</type>
    <scope>import</scope>
</dependency>
```
或者
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-all</artifactId>
    <version>2.1.2-RELEASE</version>
</dependency>
```
