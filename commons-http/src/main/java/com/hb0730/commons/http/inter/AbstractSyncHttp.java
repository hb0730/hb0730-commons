package com.hb0730.commons.http.inter;

import com.hb0730.commons.http.HttpHeader;
import com.hb0730.commons.http.config.HttpConfig;
import lombok.Getter;

/**
 * sync http 抽象
 *
 * @author bing_huang
 * @since 1.0.0
 */
public abstract class AbstractSyncHttp implements SyncHttp {
    protected HttpConfig httpConfig;
    @Getter
    protected HttpHeader header;

    public HttpConfig getHttpConfig() {
        return httpConfig;
    }

    public AbstractSyncHttp setHttpConfig(HttpConfig httpConfig) {
        this.httpConfig = httpConfig == null ? HttpConfig.builder().build() : httpConfig;
        return this;
    }

    public AbstractSyncHttp setHeader(HttpHeader header) {
        this.header = header;
        return this;
    }

    public AbstractSyncHttp(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }
}
