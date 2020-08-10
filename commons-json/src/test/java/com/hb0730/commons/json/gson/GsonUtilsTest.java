package com.hb0730.commons.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.*;
import org.junit.Test;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class GsonUtilsTest {

    @Test
    public void jsonToObjectTest() {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        System.out.println(GsonUtils.jsonToObject(json, Person.class));
    }

    @Test
    public void testJsonToObjectTest() {
        String json = "{\"id\":\"1\",\"name\":\"测试\"}";
        System.out.println(GsonUtils.jsonToObject(json, Person.class, new GsonBuilder().create()));
    }

    @Test
    public void jsonToListTest() {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        System.out.println(GsonUtils.jsonToList(json, Person.class));
    }

    @Test
    public void testJsonToListTest() {
        String json = "[{\"id\":\"1\",\"name\":\"测试1\"},{\"id\":\"2\",\"name\":\"测试2\"}]";
        System.out.println(GsonUtils.jsonToList(json, Person.class, new GsonBuilder().create()));
    }

    @Test
    public void objectToJsonTest() {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(GsonUtils.objectToJson(person));
    }

    @Test
    public void testObjectToJsonTest() {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(GsonUtils.objectToJson(person, new GsonBuilder().create()));
    }

    @Test
    public void mapToObjectTest() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        System.out.println(GsonUtils.mapToObject(map, Person.class));
    }

    @Test
    public void testMapToObjectTest() {
        Map<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "测试1");
        System.out.println(GsonUtils.mapToObject(map, Person.class, new GsonBuilder().create()));
    }

    @Test
    public void objectToMapTest() {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(GsonUtils.objectToMap(person));
    }

    @Test
    public void testObjectToMapTest() {
        Person person = Person.builder().name("测试").id("1").build();
        System.out.println(GsonUtils.objectToMap(person, new Gson()));
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
