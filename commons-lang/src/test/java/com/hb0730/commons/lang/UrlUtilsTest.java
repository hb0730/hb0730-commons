package com.hb0730.commons.lang;

import com.hb0730.commons.lang.net.UrlUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
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
}
