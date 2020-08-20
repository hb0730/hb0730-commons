package com.hb0730.commons.json;

import com.hb0730.commons.json.exceptions.JsonException;

/**
 * 抽象json
 *
 * @author bing_huang
 * @since 2.0.0
 */
public abstract class AbstractJson implements IJson {
    /**
     * Jackson {@link com.fasterxml.jackson.databind.ObjectMapper},
     * Gson {@link com.google.gson.Gson}
     */
    protected Object mapper;

    /**
     * 设置 mapper
     *
     * @param mapper Jackson {@link com.fasterxml.jackson.databind.ObjectMapper},Gson {@link com.google.gson.Gson}等
     * @throws JsonException 设置异常，非想要类型
     */
    public abstract void setMapper(Object mapper) throws JsonException;

    /**
     * 获取mapper
     *
     * @return {@link #mapper}
     */
    public abstract Object getMapper();


}
