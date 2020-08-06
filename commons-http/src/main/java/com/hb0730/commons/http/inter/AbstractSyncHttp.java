package com.hb0730.commons.http.inter;

import com.hb0730.commons.http.config.HttpConfig;
import lombok.Getter;
import lombok.Setter;

/**
 * sync http 抽象
 *
 * @author bing_huang
 * @date 2020/07/30 15:23
 * @since V1.0
 */
public abstract class AbstractSyncHttp implements SyncHttp {
    @Setter
    @Getter
    protected HttpConfig httpConfig;

    public AbstractSyncHttp(HttpConfig httpConfig) {
        this.httpConfig = httpConfig;
    }
}
