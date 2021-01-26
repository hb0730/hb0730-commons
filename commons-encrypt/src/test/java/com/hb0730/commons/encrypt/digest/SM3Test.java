package com.hb0730.commons.encrypt.digest;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.junit.Assert;
import org.junit.Test;

public class SM3Test {

    @Test
    public void sm3Test() {
        String digestHex = SM3.create(new BouncyCastleProvider()).digestHex("aaaaa");
        Assert.assertEquals("136ce3c86e4ed909b76082055a61586af20b4dab674732ebd4b599eef080c9be", digestHex);
    }
}
