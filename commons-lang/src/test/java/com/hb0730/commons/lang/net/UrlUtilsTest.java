package com.hb0730.commons.lang.net;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Slf4j
public class UrlUtilsTest {

    @Test
    public void urlTest() {
        UrlUtils urlUtils = new UrlUtils();
        Assert.assertNotNull(urlUtils);
    }

    @Test
    public void urlEncodeTest() throws UnsupportedEncodingException {
        String params = "name=1&&id=冲冲冲";
        params = UrlUtils.urlEncode(params);
        log.info(params);
        Assert.assertNotNull(params);
    }

    @Test
    public void testUrlEncodeTest() throws UnsupportedEncodingException {
        String params = "name=1&&id=冲冲冲";
        params = UrlUtils.urlEncode(params, StandardCharsets.UTF_8.displayName());
        log.info(params);
        Assert.assertNotNull(params);
    }

    @Test
    public void testUrlEncode1Test() throws UnsupportedEncodingException {
        String params = "name=1&&id=冲冲冲";
        params = UrlUtils.urlEncode(params, StandardCharsets.UTF_8);
        log.info(params);
        Assert.assertNotNull(params);

    }

    @Test
    public void urlDecoderTest() throws UnsupportedEncodingException {
        String params = "name=1&&id=冲冲冲";
        String encodeParams = UrlUtils.urlEncode(params);
        String decoderParams = UrlUtils.urlDecoder(encodeParams);
        Assert.assertEquals(params, decoderParams);
    }

    @Test
    public void testUrlDecoderTest() throws UnsupportedEncodingException {
        String params = "name=1&&id=冲冲冲";
        String encodeParams = UrlUtils.urlEncode(params, StandardCharsets.UTF_8.displayName());
        String decoderParams = UrlUtils.urlDecoder(encodeParams, StandardCharsets.UTF_8.displayName());
        Assert.assertEquals(params, decoderParams);
    }

    @Test
    public void testUrlDecoder1Test() throws UnsupportedEncodingException {
        String params = "name=1&&id=冲冲冲";
        String encodeParams = UrlUtils.urlEncode(params, StandardCharsets.UTF_8);
        String decoderParams = UrlUtils.urlDecoder(encodeParams, StandardCharsets.UTF_8);
        Assert.assertEquals(params, decoderParams);
    }

    @Test
    public void encodeTest() throws UnsupportedEncodingException {
        String params = "冲冲冲";
        String encode = UrlUtils.encode(params, (String) null);
        Assert.assertEquals(params, encode);
        encode = UrlUtils.encode(params, "UTF-8");
        Assert.assertEquals(params, encode);
    }


    @Test
    public void testEncodeTest() throws UnsupportedEncodingException {
        String params = "冲冲冲";
        String encode = UrlUtils.encode(params, (Charset) null);
        Assert.assertEquals(params, encode);
        encode = UrlUtils.encode(params, Charset.defaultCharset());
        Assert.assertEquals(params, encode);
    }

    @Test
    public void decodeTest() throws UnsupportedEncodingException {
        String params = "%E5%86%B2%E5%86%B2%E5%86%B2";
        String decode = UrlUtils.decode(params, (String) null);
        Assert.assertEquals(params, decode);
        decode = UrlUtils.decode(params, "UTF-8");
        Assert.assertEquals(params, decode);
    }

    @Test
    public void testDecodeTest() throws UnsupportedEncodingException {
        String params = "%E5%86%B2%E5%86%B2%E5%86%B2";
        String decode = UrlUtils.decode(params, (Charset) null);
        Assert.assertEquals(params, decode);
        decode = UrlUtils.decode(params, Charset.defaultCharset());
        Assert.assertEquals(params, decode);
    }
}
