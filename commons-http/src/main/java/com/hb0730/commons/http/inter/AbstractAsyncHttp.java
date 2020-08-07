package com.hb0730.commons.http.inter;

import com.hb0730.commons.http.config.HttpConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * 异步 请求 抽象
 *
 * @author bing_huang
 * @since 1.0.1
 */
public abstract class AbstractAsyncHttp implements AsyncHttp {
    @Setter
    @Getter
    protected HttpConfig httpConfig;

    public AbstractAsyncHttp(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }
}
