package com.hb0730.commons.cache;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * cache entity
 *
 * @author bing_huang
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CacheWrapper<V> implements Serializable {

    private static final long serialVersionUID = -3937692041778157956L;
    /**
     * Cache data
     */
    private V data;

    /**
     * Expired time.
     */
    private Date expireAt;

    /**
     * Create time.
     */
    private Date createAt;
}
