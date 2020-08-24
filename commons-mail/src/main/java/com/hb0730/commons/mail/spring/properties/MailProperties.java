package com.hb0730.commons.mail.spring.properties;

import lombok.Data;
import lombok.ToString;

/**
 * email 配置
 *
 * @author bing_huang
 * @since 2.0.0
 */
@Data
@ToString
public class MailProperties {
    /**
     * 发送主机
     */
    private String host;

    /**
     * 协议
     */
    private String protocol;

    /**
     * ssl 端口
     */
    private Integer sslPort;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 发件人
     */
    private String fromName;

    /**
     *是否启用
     */
    private Boolean enabled;
}
