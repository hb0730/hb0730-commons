package com.hb0730.commons.cache;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author bing_huang
 */
public class BaseTest {
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DataClass<T> implements Serializable {
        private T data;
    }
}
