package com.hb0730.commons.cache.support.redis.jedis;

import com.hb0730.commons.cache.config.CacheConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import redis.clients.jedis.JedisPool;

/**
 * jedis config
 *
 * @author bing_huang
 * @date 2020/08/04 9:07
 * @since V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class JedisCacheConfig<K, V> extends CacheConfig<K, V> {
    private JedisProperties properties;
}
