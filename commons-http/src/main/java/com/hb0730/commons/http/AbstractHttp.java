package com.hb0730.commons.http;

import com.hb0730.commons.http.config.HttpConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * http 抽象
 *
 * @author bing_huang
 * @date 2020/07/30 15:23
 * @since V1.0
 */
public abstract class AbstractHttp implements Http {
    @Setter
    @Getter
    protected HttpConfig httpConfig;

    public AbstractHttp(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }
}
