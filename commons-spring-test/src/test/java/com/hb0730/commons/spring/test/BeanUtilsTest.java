package com.hb0730.commons.spring.test;

import com.hb0730.commons.spring.BeanUtils;
import lombok.*;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author bing_huang
 * @date 2020/07/31 11:56
 * @since V1.0
 */
public class BeanUtilsTest {
    @Test
    public void transformFromTest() {
        User user = User.builder().id("1").name("小红").build();
        People people = BeanUtils.transformFrom(user, People.class);
        System.out.println(people);
    }

    @Test
    public void transformFromInBatchTest() {
        List<User> users = Lists.newArrayList(User.builder().id("1").name("小红").build());
        System.out.println(BeanUtils.transformFromInBatch(users, People.class));
    }

    @Test
    public void updatePropertiesTest() {
        User user = User.builder().id("1").name("小红").build();
        People people = People.builder().id("2").name("小黑").build();
        BeanUtils.updateProperties(user, people);
        System.out.println(user + "_" + people);
    }

    @Test
    public void map2beanTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("name", "小红");
        User user = BeanUtils.map2bean(map, User.class);
        System.out.println(user);
    }

    @Test
    public void convertToMapTest() {
        List<User> lists = new ArrayList<>();
        User user = User.builder().id("1").name("小红").build();
        lists.add(user);
        System.out.println(BeanUtils.convertToMap(lists, User::getId));
    }

    @Test
    public void convertToMapTest2() {
        List<User> lists = new ArrayList<>();
        User user = User.builder().id("1").name("小红").build();
        lists.add(user);
        System.out.println(BeanUtils.convertToMap(lists, User::getId, User::getName));
    }

    @Builder
    @Data
    @EqualsAndHashCode
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class User {
        private String id;
        private String name;
    }

    @Builder
    @Data
    @EqualsAndHashCode
    @ToString
    @AllArgsConstructor
    @NoArgsConstructor
    public static class People {
        private String id;
        private String name;
    }
}
