# commons-ssh
对 [jsch](http://www.jcraft.com/jsch/) 等 SSH client 简单封装
## maven
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-ssh</artifactId>
    <version>${version}</version>
</dependency>
```
## JSCH
### session
```java
JschSessionBuilder.builder()
.sshHost("192.168.52.128")
.sshUser("root")
.sshPort(22)
.sshPass("123456")
.build();
```
### ChannelExec
```java
JschChannelExec channelExec = JschChannelExec.builder(session);
String result = channelExec.exec("echo 'hello word'", Charset.defaultCharset());
```

相关操作请看示例
