# commons spring
对一般常用的spring工具的集合(SpringContext,Message,Bean等)
## maven
```xml
 <dependency>
     <groupId>com.hb0730</groupId>
     <artifactId>commons-spring</artifactId>
     <version>${commons.version}</version>
 </dependency>
```
## 用法
### SpringContextUtils spring上下文
```java

/**
     * 获取spring context
     *
     * @return spring context
     */
    public static ApplicationContext getApplicationContext() {
        Assert.notNull(applicationContext, "Please set it first ApplicationContext");
        return applicationContext;
    }

    /**
     * 设置spring context
     *
     * @param applicationContext spring context
     */
    public static void setApplicationContext(@NonNull ApplicationContext applicationContext) {
        SpringContextUtils.applicationContext = applicationContext;
    }
```
如果使用springBoot时可以在创建Application就直接set
```java

@SpringBootApplication
public class SpringTestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringTestApplication.class, args);
        SpringContextUtils.setApplicationContext(context);
    }
}

```
## Spring Message用法

```java
 SpringMessageUtils.message("user.password.not.match")
```
就可以获取`resources/in18/`下的资源
