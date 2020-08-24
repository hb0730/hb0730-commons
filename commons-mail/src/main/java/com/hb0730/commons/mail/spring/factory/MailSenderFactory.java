package com.hb0730.commons.mail.spring.factory;

import com.hb0730.commons.mail.spring.properties.SpringMailProperties;
import org.springframework.lang.NonNull;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Properties;

/**
 * java mail sender factory
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class MailSenderFactory {

    /**
     * 获取{@link JavaMailSender}
     *
     * @param properties mail 配置
     * @return {@link JavaMailSender}
     */
    public JavaMailSender getMailSender(@NonNull SpringMailProperties properties) {
        Assert.notNull(properties, "Mail properties must not be null");
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        setProperties(mailSender, properties);
        return mailSender;
    }

    private void setProperties(@NonNull JavaMailSenderImpl mailSender, @NonNull SpringMailProperties springMailProperties) {
        mailSender.setHost(springMailProperties.getHost());
        mailSender.setPort(springMailProperties.getPort());
        mailSender.setUsername(springMailProperties.getUsername());
        mailSender.setPassword(springMailProperties.getPassword());
        mailSender.setProtocol(springMailProperties.getProtocol());
        mailSender.setDefaultEncoding(springMailProperties.getDefaultEncoding().name());

        if (!CollectionUtils.isEmpty(springMailProperties.getProperties())) {
            Properties properties = new Properties();
            properties.putAll(springMailProperties.getProperties());
            mailSender.setJavaMailProperties(properties);
        }
    }
}
