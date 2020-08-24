# Changelog
# 2.0.0
## Feature
+ **[commons-lang]**
    + ObjectUtils新增isNotEmpty方法
    + CharUtils新增isNumber方法
    + CharUtils新增isLetterLower,isLetterUpper, isLetter等方法
## Fixed
+ **[commons-lang]**
  + 优化使用jdk7提供的安全随机数生成器ThreadLocalRandom
## Change
+ **[commons-json]**
  + 优化json创建以及多json优选
+ **[commons-mail]**
  + 新增对javaMail的支持
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
