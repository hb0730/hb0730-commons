package com.hb0730.commons.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.*;
import org.junit.Test;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class JacksonUtilsTest {

    @Test
    public void jsonToObjectTest() throws IOException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        System.out.println(JacksonUtils.jsonToObject(json, Person.class));
    }

    @Test
    public void testJsonToObjectTest() throws IOException {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        System.out.println(JacksonUtils.jsonToObject(json, Person.class, new ObjectMapper()));

    }

    @Test
    public void jsonToListTest() throws IOException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        System.out.println(JacksonUtils.jsonToList(json, Person.class));
    }

    @Test
    public void testJsonToListTest() throws IOException {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        System.out.println(JacksonUtils.jsonToList(json, Person.class, new ObjectMapper()));
    }

    @Test
    public void objectToJsonTest() throws IOException {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(JacksonUtils.objectToJson(person));
    }

    @Test
    public void testObjectToJsonTest() throws IOException {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(JacksonUtils.objectToJson(person, new ObjectMapper()));
    }

    @Test
    public void mapToObjectTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        System.out.println(JacksonUtils.mapToObject(map, Person.class));
    }

    @Test
    public void testMapToObjectTest() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        System.out.println(JacksonUtils.mapToObject(map, Person.class, new ObjectMapper()));
    }

    @Test
    public void objectToMapTest() throws IOException {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(JacksonUtils.objectToMap(person));
    }

    @Test
    public void testObjectToMapTest() throws IOException {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(JacksonUtils.objectToMap(person, new ObjectMapper()));
    }

    @Data
    @Builder
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    static class Person implements Serializable {
        private String id;
        private String name;
    }
}
