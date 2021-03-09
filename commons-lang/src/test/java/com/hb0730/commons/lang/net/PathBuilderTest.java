package com.hb0730.commons.lang.net;

import com.hb0730.commons.lang.constants.Charsets;
import org.junit.Assert;
import org.junit.Test;

public class PathBuilderTest {

    @Test
    public void buildTest() {
        String s = PathBuilder.builder("/test1/test2/test3/", null).addAfter("/test4/test5/test6/").setWithEngTag(true).build();
        Assert.assertNotNull(s);
    }

    @Test
    public void parseTest() {
        String s = PathBuilder
                .builder("/test1/test2/test3/", null)
                .parse("/dd1/dd2", null)
                .addAfter("/test4/test5/test6/")
                .setWithEngTag(true)
                .build();
        Assert.assertNotNull(s);
    }

    @Test
    public void testBuildTest() {
        String s = PathBuilder
                .builder("/test1/test2/test3/", Charsets.UTF_8)
                .parse("/dd1/dd2", Charsets.UTF_8)
                .addAfter("/test4/test5/test6/")
                .addBefore("/java-测试")
                .setWithEngTag(true)
                .build();
        Assert.assertNotNull(s);
    }
}
