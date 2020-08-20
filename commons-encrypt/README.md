#   commons encrypt
加密与解密，本项目来源https://github.com/duanxinyuan/cipher-utils
## Maven依赖
```xml
<dependency>
    <groupId>com.hb0730</groupId>
    <artifactId>commons-encrypt</artifactId>
    <version>${commons.version}</version>
</dependency>
```
## 已包含加密算法
+ Hmac
+ MD5
+ SHA
+ RSA
+ AES
+ DESede
+ DES
+ RC4
+ SM2
+ SM3
+ SM4
## 非对称加密
+ RSAUtils（国际非对称加密标准）
+ SM2Utils（椭圆曲线公钥密码算法，国内非对称加密标准）
## 对称加密
+ AESUtils（高级加密标准(Advanced Encryption Standard)）
+ DESUtils（美国数据加密标准(Data Encryption Standard)）
+ DESedeUtils（三重DES）
+ RC4Utils
+ SM4Utils（无线局域网标准的分组数据算法，国密对称加密）
## 散列/摘要/杂凑
+ HmacUtils
+ MD5Utils
+ SHAUtils
+ SM3Utils（国密杂凑算法）

## 依赖
+ apache commons-codec
+ bouncycastle

## link
+ [Java 下常用密码算法的使用](https://www.mingfer.cn/2019/09/29/use-bc-java/)
