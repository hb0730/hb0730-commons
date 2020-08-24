package com.hb0730.commons.mail.spring.properties;

import com.hb0730.commons.lang.StringUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * mail properties
 *
 * @author bing_huang
 */
@Data
@EqualsAndHashCode
public class SpringMailProperties {
    private static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
    /**
     * SMTP server host. For instance, `smtp.example.com`.
     */
    private String host;
    /**
     * SMTP server port.
     */
    private Integer port;

    /**
     * Login user of the SMTP server.
     */
    private String username;

    /**
     * Login password of the SMTP server.
     */
    private String password;

    /**
     * Protocol used by the SMTP server.
     */
    private String protocol = "smtp";

    /**
     * Default MimeMessage encoding.
     */
    private Charset defaultEncoding = DEFAULT_CHARSET;

    /**
     * Additional JavaMail Session properties.
     */
    private Map<String, String> properties = new HashMap<>();

    /**
     * Session JNDI name. When set, takes precedence over other Session settings.
     */
    private String jndiName;

    public SpringMailProperties(boolean needDebug) {

    }

    public void addProperties(String key, String value) {
        if (null == this.properties) {
            properties = new HashMap<>();
        }
        this.properties.put(key, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        final String lineSuffix = ",\n";
        final StringBuffer sb = new StringBuffer();
        sb.append("MailProperties{").append(lineSuffix);
        sb.append("host=").append(getHost()).append(lineSuffix);
        sb.append("username=").append(getUsername()).append(lineSuffix);
        sb.append("password=").append(StringUtils.isBlank(getPassword()) ? "<null>" : "<non-null>");
        sb.append("port=").append(getPort()).append(lineSuffix);
        sb.append("protocol=").append(getProtocol()).append(lineSuffix);
        sb.append("defaultEncoding=").append(getDefaultEncoding()).append(lineSuffix);
        sb.append("properties=").append(properties).append(lineSuffix);
        sb.append('}');
        return sb.toString();
    }
}
