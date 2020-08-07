package com.hb0730.commons.cache.support.redis.jedis;

import lombok.Data;

/**
 * redis properties
 *
 * @author bing_huang
 * @since 1.0.1
 */
@Data
public class JedisProperties {
    /**
     * 主机地址.
     */
    private String host = "127.0.0.1";

    /**
     * 端口号.
     */
    private int port = 6379;

    /**
     * 密码.
     */
    private String password;

    /**
     * 超时.
     */
    private int timeout = 2000;

    /**
     * 数据库.
     */
    private int database = 0;

    private Integer maxActive;
    private Integer maxIdle;
    private Integer maxWaitMillis;
    private Integer minIdle;
}
