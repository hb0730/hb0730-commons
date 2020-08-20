package com.hb0730.commons.json;

import com.hb0730.commons.json.exceptions.JsonException;

import java.util.List;
import java.util.Map;

/**
 * json 接口
 *
 * @author bing_huang
 * @since 2.0.0
 */
public interface IJson {
    /**
     * json 字符串转对象,会使用默认转换
     *
     * @param json json字符串，不为空
     * @param type 需要转换的类型，不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     * @see #jsonToObject(String, Class, Object)
     */
    <T> T jsonToObject(String json, Class<T> type) throws JsonException;

    /**
     * json 字符串转对象
     *
     * @param json   json字符串，不为空
     * @param type   需要转换的类型，不为空
     * @param mapper 如Jackson{@link com.fasterxml.jackson.databind.ObjectMapper},Gson{@link com.google.gson.Gson},不为空
     * @param <T>    对象转换类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     */
    <T> T jsonToObject(String json, Class<T> type, Object mapper) throws JsonException;

    /**
     * json字符串转list
     *
     * @param json json字符串不为空
     * @param type 转换的类型,不为空
     * @param <T>  对象转换类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     * @see #jsonToList(String, Class, Object)
     */
    <T> List<T> jsonToList(String json, Class<T> type) throws JsonException;

    /**
     * json 字符串转list对象
     *
     * @param json   json字符串，不为空
     * @param type   需要转换的类型，不为空
     * @param mapper 如Jackson{@link com.fasterxml.jackson.databind.ObjectMapper},Gson{@link com.google.gson.Gson},不为空
     * @param <T>    对象转换类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     */
    <T> List<T> jsonToList(String json, Class<T> type, Object mapper) throws JsonException;

    /**
     * 将对象转换成string json
     *
     * @param source 目标对象，不为空
     * @return json string
     * @throws JsonException 转换异常
     * @see #objectToJson(Object, Object)
     */
    String objectToJson(Object source) throws JsonException;

    /**
     * 将对象转换成string json
     *
     * @param source 目标对象，不为空
     * @param mapper 如Jackson{@link com.fasterxml.jackson.databind.ObjectMapper},Gson{@link com.google.gson.Gson},不为空
     * @return json string
     * @throws JsonException 转换异常
     */
    String objectToJson(Object source, Object mapper) throws JsonException;

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param <T>       目标对象类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     * @see #mapToObject(Map, Class, Object)
     */
    <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type) throws JsonException;

    /**
     * 将map类型转成对象类型
     *
     * @param sourceMap 源map类型对象，不为空
     * @param type      需要转成的对象类型，不为空
     * @param mapper    如Jackson{@link com.fasterxml.jackson.databind.ObjectMapper},Gson{@link com.google.gson.Gson},不为空
     * @param <T>       目标对象类型
     * @return 对象指定类型
     * @throws JsonException 转换异常
     */
    <T> T mapToObject(Map<String, ?> sourceMap, Class<T> type, Object mapper) throws JsonException;

    /**
     * 将 源目标对象转成map类型
     *
     * @param source 源目标，不为空
     * @return map实例
     * @throws JsonException 转换异常
     * @see #objectToMap(Object, Object)
     */
    Map<?, ?> objectToMap(Object source) throws JsonException;

    /**
     * 将 源目标对象转成map类型
     *
     * @param source 源目标，不为空
     * @param mapper 如Jackson{@link com.fasterxml.jackson.databind.ObjectMapper},Gson{@link com.google.gson.Gson},不为空
     * @return map实例
     * @throws JsonException 转换异常
     */
    Map<?, ?> objectToMap(Object source, Object mapper) throws JsonException;
}
