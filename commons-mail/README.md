# commons mail

## 使用Javax Mail

### Maven

```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-mail</artifactId>
    <version>${version}</version>
</dependency>
```
### 依赖
```xml
 <dependency>
    <groupId>com.sun.mail</groupId>
    <artifactId>jakarta.mail</artifactId>
    <version>${jakarta-mail.version}</version>
</dependency>
```
### 如何使用

```java
 @Test
public void sendByAccountTest() {
    MailAccount account = new MailAccount()
            .host("smtp.qq.com")
            .port(465)
            .user("xxx@qq.com")
            .password("")
            .sslEnable(true)
            .from("xxx");
    Mail.create(account)
            .tos("xxx@gmail.com")
            .subject("测试")
            .content("<h2>测试</h2>")
            .html(true)
            .send();
}

@Before
public void before() {
    GlobalMailAccount.INSTANCE
    .user("xxx@qq.com")
    .password("")
    .host("smtp.qq.com")
    .port(465)
    .debug(true)
    .auth(true)
    .sslEnable(true)
    .from("xxx");
}

@Test
public void sendWithFileTest() {
    Mail.create()
    .tos("xxx@gmail.com")
    .subject("测试")
    .content("<h2>测试</h2>")
    .html(true)
    .addAttachments(new File("d:/haha3.txt"))
    .send();
}
```

## 使用spring Mail进行Email的发送

### Maven

```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-mail</artifactId>
    <version>${version}</version>
</dependency>
```

### 依赖

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-context-support</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>${spring.version}</version>
</dependency>
<dependency>
    <groupId>org.freemarker</groupId>
    <artifactId>freemarker</artifactId>
    <version>${freemarker.version}</version>
</dependency>
```

### 如何使用

```java
public class MailServiceImplTest {
    private AbstractMailService service;
    
    @Before
    public void init() {
        MailProperties properties = new MailProperties();
        properties.setEnabled(true);
        properties.setHost("smtp.qq.com");
        properties.setProtocol("smtps");
        properties.setSslPort(465);
        properties.setUsername("xxxx@qq.com");
        properties.setPassword("xxx");
        properties.setFromName("xxx");
        service = new MailServiceImpl(properties, null);
    }
    
    @Test
    public void sendTextMailTest() throws InterruptedException {
        service.sendTextMail("xxxx@qq.com", "测试mail", "车擦擦擦错");
        Assert.fail("成功");
    }
    
    @Test
    public void testConnectionTest() {
        service.testConnection();
        Assert.fail("成功");
    }
}
```
