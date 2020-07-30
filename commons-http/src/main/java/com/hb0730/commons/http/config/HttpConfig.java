package com.hb0730.commons.http.config;

import com.hb0730.commons.http.constants.Constants;
import lombok.*;

import java.net.Proxy;

/**
 * Http 配置类
 *
 * @author bing_huang
 * @date 2020/07/30 15:20
 * @since V1.0
 */
@Data
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class HttpConfig {
    /**
     * 代理配置
     */
    private Proxy proxy;
    /**
     * 超时时长，单位毫秒
     */
    private int timeout = Constants.DEFAULT_TIMEOUT;

    /**
     * 编码
     */
    private boolean encode = false;

    /**
     * 解码
     */
    private boolean decode = false;
}
