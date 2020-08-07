package com.hb0730.commons.cache.support.redis.springdata;

import com.hb0730.commons.cache.config.CacheConfig;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * spring data redis cache configuration
 *
 * @author bing_huang
 * @since 1.0.0
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class RedisSpringDataCacheConfig<K, V> extends CacheConfig<K, V> {

    private RedisConnectionFactory connectionFactory;
}
