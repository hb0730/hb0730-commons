package com.hb0730.commons.lang.map;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

@Slf4j
public class MapBuilderTest {

    @Test
    public void buildTest() {
        MapBuilder<String, String> map = MapBuilder.builder();
        Assert.assertNotNull("创建失败，map为空", map);
    }

    @Test
    public void testBuildTest() {
        MapBuilder<String, String> map = MapBuilder.builder(25);
        Assert.assertNotNull("创建失败，map为空", map);
    }

    @Test
    public void testBuild1Test() {
        MapBuilder<String, String> map = MapBuilder.builder(MapUtils.newHashMap());
        Assert.assertNotNull("创建失败，map为空", map);
    }

    @Test
    public void builderTest() {
        MapBuilder<String, String> map = MapBuilder.builder();
        Assert.assertNotNull("创建map失败", map);
        Map<String, String> builder = map.build();
        Assert.assertNotNull("创建map失败", builder);
    }

    @Test
    public void putTest() {
        MapBuilder<String, String> map = MapBuilder.builder();
        Assert.assertNotNull("创建map失败", map);
        map.put("str", "sr");
        Map<String, String> builder = map.build();
        Assert.assertNotNull("创建map失败", builder);
        log.debug(builder.toString());
    }

    @Test
    public void putAllTest() {
        MapBuilder<String, String> map = MapBuilder.builder();
        Assert.assertNotNull("创建map失败", map);
        map.put("str", "sr");
        Map<String, String> map2 = MapUtils.newHashMap();
        map2.put("1", "1");
        map2.put("2", "2");
        map.putAll(map2);
        Map<String, String> builder = map.build();
        Assert.assertNotNull("创建map失败", builder);
        log.debug(builder.toString());
    }
}
