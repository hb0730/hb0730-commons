package com.hb0730.commons.lang.net;

import com.hb0730.commons.lang.map.MapBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
public class QueryBuilderTest {

    @Test
    public void builderTest() {
        String query = QueryBuilder.builder()
                .add("q1", "test")
                .add("q2", "test2")
                .build();
        Assert.assertNotNull(query);
    }

    @Test
    public void testBuilderTest() {
        Map<String, String> querys = MapBuilder.builder(new LinkedHashMap<String, String>())
                .put("q1", "test1")
                .put("q2", "test2")
                .build();
        String query = QueryBuilder.builder(StandardCharsets.UTF_8)
                .addAll(querys)
                .build();
        Assert.assertNotNull(query);
    }

    @Test
    public void testBuilder1Test() {
        Map<String, String> querys = MapBuilder.builder(new LinkedHashMap<String, String>())
                .put("q1", "test1")
                .put("q2", "test2")
                .build();
        String query = QueryBuilder.builder(querys)
                .add("q3", "test3")
                .build();
        Assert.assertNotNull(query);
    }

    @Test
    public void testBuilder2Test() {
        Map<String, String> querys = MapBuilder.builder(new LinkedHashMap<String, String>())
                .put("q1", "test1")
                .put("q2", "test2")
                .build();
        String query = QueryBuilder.builder(querys, StandardCharsets.UTF_8)
                .add("q3", "test3")
                .build();
        Assert.assertNotNull(query);
    }

    @Test
    public void builder1Test() {
        String pathParams = "https://baidu.com/1/2/3?a=1&b=3&c=4";
        String query = QueryBuilder.builder(pathParams)
                .build();
        Assert.assertNotNull(query);
    }

    @Test
    public void testBuilder3Test() {
        String pathParams = "https://baidu.com/1/2/3?a=1&b=3&c=4";
        String query = QueryBuilder.builder(pathParams, StandardCharsets.UTF_8)
                .build();
        Assert.assertNotNull(query);
    }

    @Test
    public void getQuerysTest() {
        Map<String, String> querys = MapBuilder.builder(new LinkedHashMap<String, String>())
                .put("q1", "test1")
                .put("q2", "test2")
                .build();
        Map<CharSequence, CharSequence> map = QueryBuilder.builder(querys, StandardCharsets.UTF_8)
                .add("q3", "test3")
                .getQuerys();

        Assert.assertNotNull(map);
    }

    @Test
    public void getTest() {
        Map<String, String> querys = MapBuilder.builder(new LinkedHashMap<String, String>())
                .put("q1", "test1")
                .put("q2", "test2")
                .build();
        CharSequence sequence = QueryBuilder.builder(querys, StandardCharsets.UTF_8)
                .add("q3", "test3")
                .get("q3");
        Assert.assertNotNull(sequence);

    }
}
