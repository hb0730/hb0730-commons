# Changelog
# 2.1.3
## Feature
+ **[commons-ssh]**
  + 对`JSch`新增`Channel#Connect`方法
# 2.1.2
## Feature
+ **[commons-lang]**
  * 新增`ListUtils#list(boolean isLinked, Enumeration<T> enumeration)`支持`Enumeration`转`List`
  + 新增`ClassLoaderUtils`
  + 新增`ResourcesUtils`资源工具类
+ 新增 **[commons-ssh]**  模块
+ 新增 **[commons-scm]** 模板
+ **[commons-json]**
  + 新增对jackson 泛型擦除问题新增`#jsonObject(String json, JavaType javaType)` 方法
## Fixed
## Update
# 2.1.1
## Feature
+ **[commons-lang]**
  + 完善`FileUtils`方法
  + 完善 `IOUtils`方法  
  + 新增 `CollectionUtils#addAll`方法
  + 新增 `FileTypeUtils`类
  + 新增 `StringUtils#joinNotNull` 排除Null
  + 新增 `ArrayUtils#join`
  + 新增 `LocalDateUtils`
  + 完善 `Thread`相关
  + 新增 `ListBuilder`
  + 新增 `StringUtils#removePrefix`等方法
  + 新增 `net`包下相关信息
+ **[commons-spring]**
  + 新增`resourcesUtils`
  
## Fixed
+ **[commons-spring]**
  + update tomcat version 
## Update
+ **[commons-lang]**
  + update `MapBuilder` 更改继承方式为: `Builder<T>`
# 2.1.0
## Feature
+ **[commons-lang]**
  +  新增 `ArrayUtils#isNotEmpty(Object)`等方法
  +  新增 `HexUtils#encodeHexString(byte[])` 方法
  +  新增 `RandomUtils#randomBytes` 方法
  +  新增 `RegexUtils#isHex` 等方法
+ **[commons-encrypt]**
  + 新增 **Digester**摘要器
  + 新增 **MD5**摘要算法
  + 新增 **HMac**摘要算法
  + 新增 **SM3**摘要算法
  + 新增 **AES**加密算法
  + 新增 **DES**加密算法
  + 新增 **DESede**加密算法
  + 新增 **SM4**加密算法
+ **[commons-mail]**
  + 新增`javax.mail`方式
  
## Fixed
+ **[commons-encrypt]**
  + fixed `MD5Utils#md5WithSalt` 加盐摘要错误
+ **[commons-lang]**
  + fixed `ArrayUtils#isEmpty` 错误
  + fixed `HexUtils#encodeHex` 注释与返回值不一致

## update

# 2.0.4
## Feature
+ **[commons-lang]**
  + 新增`MapUtils#defaultIfEmpty`为空返回默认
+ **[commons-cache]**
  + 新增 **GenericJackson2JsonCacheWrapperSerializer** 类,用来对泛型的支持, see [GenericJackson2JsonRedisSerializer](https://github.com/spring-projects/spring-data-redis/blob/master/src/main/java/org/springframework/data/redis/serializer/GenericJackson2JsonRedisSerializer.java)
+ **[commons-spring]** 
  + 新增 `SpringContextUtils#getProperty`等方法
  + 新增 `AopTargetUtils`对aop代理相关工具类
  
## Fixed
+ **[commons-cache]**
  + 移除 `GlobalSerializeMap` 
  + fixed jackson2反序列化泛型擦除问题
  
## Change
+ **[commons-cache]**
  + 更新 `Serializer#serialize` , `Serializer#deserialize` 抛出的异常信息
+ **[commons-http]**
  + 移除 `HttpCallback#success(byte[] result)` method
+ **[commons-json]**
  + update Exception为RuntimeException
  
# 2.0.3
## Feature
+ **[commons-lang]**
    + 新增 `MapBuilder` 链式map
    + 新增 `HexUtils` 16进制转换
## Fixed

## Change
+ **[commons-json]**
    + `Jsons`改成枚举类型(安全单例)
+ **[commons-http]**
    + 将`CommonHttps`改成`Https`,并优化创建方式
    + `httpConfig#timeout`的`int`改为`long`
    + **重构http,删除相关`header`相关方法，使用`setter`**
    + `HttpHeader`新增`builder`方法
    + 重命名`CommonsNetCall`为`HttpCallback`
    + 重命名`CommonHttpException`为`HttpException`
    + 方法参数重排
+ **[commons-lang]**
    + 将`MapUtils`从`collection`包迁址至`map`包下
+ **[commons-encrypt]**
    + 移除对`commons-codec:1.13`的依赖
# 2.0.2
## Feature
+ **[commons-lang]**
    + StringUtils#join 拼接对个string类型的参数
+ **[commons-json]**
    + Jsons#getCurrentJson 获取当前IJson
## Fixed
+ **[commons-cache]**
    + 当使用jdk8的LocalDateTime时jackson序列化出现问题
## Change
+ **[commons-json]**
    + 异常提示
+ **[commons-cache]**
    + try catch 异常响应问题
# 2.0.1
## Feature
+ **[commons-lang]**
    + CollectionUtils新增isNotEmpty方法
    + 新增建造者模式的接口定义Builder
## Fixed
+ **[commons-json]**
    + 修复Jsons创建方法以及Jsons转map时重写问题
## Change
+ **[commons-lang]**
    + convert移除无用的工具类ConvertUtils
+ **[commons-mail]**
    + spring MailService新增clearCache清除缓存方法
# 2.0.0
## Feature
+ **[commons-lang]**
    + ObjectUtils新增isNotEmpty方法
    + CharUtils新增isNumber方法
    + CharUtils新增isLetterLower,isLetterUpper, isLetter等方法
    + 新增IpUtils工具类
    + ConverterRegistry新增convert等方法
+ **[commons-spring]**
    + 新增IpUtils工具类实现请求获取ip
+ **[commons-cache]**
    + 新增#get(keys)根据keys获取,#delete(keys)根据keys删除 集合操作
## Fixed
+ **[commons-lang]**
  + 优化使用jdk7提供的安全随机数生成器ThreadLocalRandom
+ **[commons-http]**
  + 修复httpClient设置请求头可能抛出NullPointerException
+ **[commons-cache]**
  + 优化InMemoryCache 锁的方式以及新增有参构造，运行自定义map类型
+ **[commons-json]**
  + 修复JsonToMap序列化问题导致异常
## Change
+ **[commons-json]**
  + 优化json创建以及多json优选
+ **[commons-mail]**
  + 新增对javaMail的支持
+ **[commons-lang]**
  + 位置转移:UrlUtils
# 1.0.2
## Feature
- commons-lang
    + 新增BasicTypeEnum 基本类型与包装类型 枚举
    + 新增ClassUtils#isAllAssignableFrom,ClassUtils#isBasicType等方法
    + 新增ReflectUtils#getMethod等方法
    + 新增Converter 类型转换
    + 新增classUtils#getTypeArgument,classUtils#isEnum等方法
    + 新增TypeUtils类 和 ReflectUtils#getConstructor等方法
    + 新增DateUtils#format等方法
    + 新增关于Date的相关操作和类以及ObjectUtils方法
    + 新增Base64工具类
    + 新增ListUtils工具类
    + 新增CollectionUtils#emptyIfNull等方法
    + 新增MapUtils#sort等方法
    + 新增ModifierUtils工具类
    + 新增ClassUtils中isNormalClass和isAbstract方法
    + 新增ReflectUtils中getField方法
    + 新增BeanInfoCache类
    + 新增BeanUtils中对Bean描述的获取getPropertyDescriptor,getPropertyDescriptorMap等
- commons-encrypt
    + 新增加密解密模块，对常规加密解密的支持MD5,SHA等
## Fixed

## Change
- commons-http
    + 对commonHttps的优化，
    + 对HttpsAsyncUtils和HttpSyncUtils的重命名，以及继承关系的修改
# 1.0.1
## Feature
- commons-lang
    - 新增ObjectUtils类
    - 新增Exceptions类
    - 新增DateUtils#between等方法
    - 新增ReflectUtils类
    - 新增RandomUtils类
    - 新增classUtils#getDefaultValue等方法
    - 新增IOUtils类
    - 新增constants类
    - 新增SystemUtils类
- commons-cache
    - 新增Cache模块下对JedisPool Cache支持
- commons-http
    - 新增对http模块Async支持
- commons-json
    - 新增json模块支持fastjson,gson,jackson的支持
## Fixed
- commons-lang
    + fix UrlUtils 参数判断
- commons-cache
    + fix cache 序列化与反序列化失败问题
## Change
- commons-cache移除第三方工具类

# 1.0.0
- init project
- add tools...
