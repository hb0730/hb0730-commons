package com.hb0730.commons.http.test.model;

import java.io.Serializable;

/**
 * @author bing_huang
 * @date 2020/08/06 13:32
 * @since V1.0
 */
public class TestVO implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
