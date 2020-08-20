package com.hb0730.commons.json.utils;

import com.hb0730.commons.json.AbstractJson;
import com.hb0730.commons.json.IJson;
import com.hb0730.commons.json.exceptions.JsonException;
import com.hb0730.commons.json.fastjson.FastJsons;
import com.hb0730.commons.json.gson.Gsons;
import com.hb0730.commons.json.jackson.Jacksons;
import com.hb0730.commons.lang.ClassUtils;

import java.util.List;
import java.util.Map;

/**
 * json集合
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class Jsons implements IJson {
    private AbstractJson proxy;

    private void selectProxy() throws JsonException {
        AbstractJson defaultProxy = null;
        ClassLoader classLoader = Jsons.class.getClassLoader();
        if (ClassUtils.isPresent("com.alibaba.fastjson.JSON", classLoader)) {
            defaultProxy = getProxy(FastJsons.class);
        }
        if (ClassUtils.isPresent("com.google.gson.Gson", classLoader)) {
            defaultProxy = getProxy(Gsons.class);
        }
        if (ClassUtils.isPresent("com.fasterxml.jackson.databind.ObjectMapper", classLoader)) {
            defaultProxy = getProxy(Jacksons.class);
        }
        if (defaultProxy == null) {
            throw new JsonException("Has no HttpImpl defined in environment!");
        }
        proxy = defaultProxy;
    }

    private <T extends AbstractJson> AbstractJson getProxy(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (Throwable e) {
            return null;
        }
    }

    /**
     * 设置对应的json
     *
     * @param support 支持的json
     * @return this
     */
    public Jsons setJson(AbstractJson support) {
        proxy = support;
        return this;
    }

    /**
     * 设置对应的json
     *
     * @param support 所支持的json
     * @return this
     */
    public Jsons setJson(Class<? extends AbstractJson> support) {
        proxy = getProxy(support);
        return this;
    }

    private void checkProxyNotNull(AbstractJson proxy) throws JsonException {
        if (null == proxy) {
            selectProxy();
        }
    }


    @Override
    public <T> T jsonToObject(String json, Class<T> type) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.jsonToObject(json, type);
    }

    @Override
    public <T> T jsonToObject(String json, Class<T> type, Object mapper) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.jsonToObject(json, type, mapper);
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> type) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.jsonToList(json, type);
    }

    @Override
    public <T> List<T> jsonToList(String json, Class<T> type, Object mapper) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.jsonToList(json, type, mapper);
    }

    @Override
    public String objectToJson(Object source) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.objectToJson(source);
    }

    @Override
    public String objectToJson(Object source, Object mapper) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.objectToJson(source, mapper);
    }

    @Override
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.mapToObject(sourceMap, type);
    }

    @Override
    public <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Object mapper) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.mapToObject(sourceMap, type, mapper);
    }

    @Override
    public Map<?, ?> objectToMap(Object source) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.objectToMap(source);
    }

    @Override
    public Map<?, ?> objectToMap(Object source, Object mapper) throws JsonException {
        checkProxyNotNull(proxy);
        return proxy.objectToMap(source, mapper);
    }


    public static class Utils {
        private static Jsons jsons = new Jsons();

        public static Jsons instance() {
            return jsons;
        }
    }
}
