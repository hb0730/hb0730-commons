package com.hb0730.commons.cache;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

/**
 * cache entity
 *
 * @author bing_huang
 * @date 2020/07/18 13:28
 * @since V1.0
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
