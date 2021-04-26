package com.hb0730.commons.encrypt.asymmetric;

import com.hb0730.commons.encrypt.constant.SM2SignType;
import com.hb0730.commons.encrypt.pojo.SM2KeyPair;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Assert;
import org.junit.Test;

@Slf4j
public class SM2UtilsTest {
    private String content = "hello world";

    @Test
    public void generateKey() {
        SM2KeyPair sm2KeyPair = SM2Utils.generateKey(128);
        log.info(sm2KeyPair.getPublicKey());
        log.info(sm2KeyPair.getPrivateKey());

        log.info("私钥:" + ByteUtils.toHexString(sm2KeyPair.getEcPrivateKey().getS().toByteArray()).toUpperCase());
        log.info("公钥点X坐标:" + sm2KeyPair.getEcPublicKey().getW().getAffineX());
        log.info("公钥点Y坐标:" + sm2KeyPair.getEcPublicKey().getW().getAffineY());
        log.info("公钥点:" + ByteUtils.toHexString(sm2KeyPair.getEcPublicKey().getEncoded()).toUpperCase());
    }

    @Test
    public void test() {
        SM2KeyPair sm2KeyPair = SM2Utils.generateKey();

        //加密
        String encryptedData = SM2Utils.encrypt(content, sm2KeyPair.getEcPublicKey());
        log.info(encryptedData);

        //解密
        String decryptedData = SM2Utils.decrypt(encryptedData, sm2KeyPair.getEcPrivateKey());
        log.info(decryptedData);
        Assert.assertEquals(decryptedData, content);

        //加密
        String encryptedData1 = SM2Utils.encrypt(content, sm2KeyPair.getPublicKey());
        log.info(encryptedData1);

        //解密
        String decryptedData1 = SM2Utils.decrypt(encryptedData1, sm2KeyPair.getPrivateKey());
        log.info(decryptedData1);
        Assert.assertEquals(decryptedData1, content);
    }

    @Test
    public void testProperty() {
        SM2KeyPair sm2KeyPair = SM2Utils.generateKey();

        int count = 100;

        long startTime = System.currentTimeMillis();
        for (int i = count; i >= 0; i--) {
            SM2Utils.encrypt(content, sm2KeyPair.getEcPublicKey());
        }
        log.info("加密" + count + "次，耗时" + (System.currentTimeMillis() - startTime) + "毫秒");

        //加密
        String encryptedData = SM2Utils.encrypt(content, sm2KeyPair.getEcPublicKey());
        log.info(encryptedData);


        //解密
        startTime = System.currentTimeMillis();
        for (int i = count; i >= 0; i--) {
            SM2Utils.decrypt(encryptedData, sm2KeyPair.getEcPrivateKey());
        }
        log.info("解密" + count + "次，耗时" + (System.currentTimeMillis() - startTime) + "毫秒");
    }

    @Test
    public void sign() {
        SM2KeyPair sm2KeyPair = SM2Utils.generateKey();

        String sign = SM2Utils.sign(SM2SignType.SHA256withSM2, content, sm2KeyPair.getEcPrivateKey());
        log.info(sign);
        log.info(SM2Utils.verifySign(SM2SignType.SHA256withSM2, content, sm2KeyPair.getEcPublicKey(), sign) + "");
    }

}
