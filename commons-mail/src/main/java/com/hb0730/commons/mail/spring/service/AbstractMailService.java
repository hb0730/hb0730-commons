package com.hb0730.commons.mail.spring.service;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.mail.exceptions.MailException;
import com.hb0730.commons.mail.spring.factory.MailSenderFactory;
import com.hb0730.commons.mail.spring.properties.MailProperties;
import com.hb0730.commons.mail.spring.properties.SpringMailProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;
import org.springframework.util.Assert;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * mail 抽象类
 *
 * @author bing_huang
 * @since 2.0.0
 */
public abstract class AbstractMailService implements MailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMailService.class);
    private static final int DEFAULT_POOL_SIZE = 5;
    private String cacheFromName;
    private JavaMailSender cachedMailSender;
    private SpringMailProperties cachedMailProperties;
    private final MailProperties properties;
    private volatile ExecutorService executorService;

    public AbstractMailService(MailProperties properties) {
        this.properties = properties;
    }

    public void setExecutorService(@Nullable ExecutorService executorService) {
        this.executorService = executorService;
    }

    /**
     * 获取线程池
     *
     * @return {@link ExecutorService}
     */
    @NonNull
    public ExecutorService getExecutorService() {
        if (null == this.executorService) {
            synchronized (this) {
                if (null == this.executorService) {
                    this.executorService = new ThreadPoolExecutor(
                            DEFAULT_POOL_SIZE,
                            DEFAULT_POOL_SIZE,
                            0L,
                            TimeUnit.MILLISECONDS,
                            new LinkedBlockingQueue<Runnable>(), new CustomizableThreadFactory("commonsMail-Pool"));
                }
            }
        }
        return executorService;
    }

    /**
     * 获取{@link JavaMailSender}
     *
     * @return {@link JavaMailSender}
     */
    public synchronized JavaMailSender getMailSender() {
        if (null == this.cachedMailSender) {
            MailSenderFactory factory = new MailSenderFactory();
            this.cachedMailSender = factory.getMailSender(getMailProperties());
        }
        return this.cachedMailSender;
    }

    /**
     * 发送模板消息
     *
     * @param callback 回调函数 不为空
     */
    protected void sendMailTemplate(@Nullable Callback callback) {
        if (null == callback) {
            LOGGER.info("Callback is null, skip to send email");
            return;
        }
        Boolean enabled = properties.getEnabled();
        if (!enabled) {
            // If disabled
            LOGGER.info("Email has been disabled by yourself, you can re-enable it through email settings on admin page.");
            return;
        }

        JavaMailSender javaMailSender = getMailSender();
        MimeMessageHelper messageHelper = new MimeMessageHelper(javaMailSender.createMimeMessage());
        try {
            messageHelper.setFrom(getFromAddress(javaMailSender));
            callback.handle(messageHelper);
            MimeMessage mimeMessage = messageHelper.getMimeMessage();
            javaMailSender.send(mimeMessage);
            LOGGER.info("Sent an email to [{}] successfully, subject: [{}], sent date: [{}]",
                    Arrays.toString(mimeMessage.getAllRecipients()),
                    mimeMessage.getSubject(),
                    mimeMessage.getSentDate());
        } catch (Exception e) {
            e.printStackTrace();
            throw new MailException("邮件发送失败，请检查 SMTP 服务配置是否正确", e);
        }

    }

    /**
     * 如果线程池是启用状态则发送模板消息
     *
     * @param tryToAsync 是否异步
     * @param callback   回调
     */
    protected void sendMailTemplate(boolean tryToAsync, @NonNull Callback callback) {
        ExecutorService executorService = getExecutorService();
        if (tryToAsync) {
            executorService.execute(() -> sendMailTemplate(callback));
        } else {
            sendMailTemplate(callback);
        }
    }

    /**
     * Test connection with email server.
     */
    @Override
    public void testConnection() {
        JavaMailSender javaMailSender = getMailSender();
        if (javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            try {
                mailSender.testConnection();
            } catch (MessagingException e) {
                throw new MailException("无法连接到邮箱服务器，请检查邮箱配置", e);
            }
        }
    }

    /**
     * 获取mail 配置
     *
     * @return {@link SpringMailProperties}
     */
    private synchronized SpringMailProperties getMailProperties() {
        if (null == this.cachedMailProperties) {
            SpringMailProperties mailProperties = new SpringMailProperties(LOGGER.isDebugEnabled());
            mailProperties.setHost(this.properties.getHost());
            mailProperties.setPort(this.properties.getSslPort());
            mailProperties.setUsername(this.properties.getUsername());
            mailProperties.setPassword(this.properties.getPassword());
            mailProperties.setProtocol(this.properties.getProtocol());
            this.cachedMailProperties = mailProperties;
        }
        return this.cachedMailProperties;
    }

    /**
     * 获取接收地址
     *
     * @param javaMailSender java mail sender
     * @return 接收网络地址
     * @throws UnsupportedEncodingException 提供错误的字符编码时引发
     */
    private synchronized InternetAddress getFromAddress(@NonNull JavaMailSender javaMailSender) throws UnsupportedEncodingException {
        Assert.notNull(javaMailSender, "Java mail sender must not be null");
        if (StringUtils.isBlank(this.cacheFromName)) {
            this.cacheFromName = this.properties.getFromName();
        }
        if (javaMailSender instanceof JavaMailSenderImpl) {
            JavaMailSenderImpl mailSender = (JavaMailSenderImpl) javaMailSender;
            String username = mailSender.getUsername();
            return new InternetAddress(username, this.cacheFromName, mailSender.getDefaultEncoding());
        }
        throw new UnsupportedEncodingException("Unsupported java mail sender: " + javaMailSender.getClass().getName());
    }

    /**
     * 清除缓存
     */
    @Override
    public void clearCache() {
        this.cachedMailProperties = null;
        this.cacheFromName = null;
        this.cachedMailSender = null;
        LOGGER.debug("Cleared all mail caches");
    }

    /**
     * 回调
     */
    protected interface Callback {
        /**
         * 处理消息集
         *
         * @param messageHelper mime消息帮助程序
         * @throws Exception if something goes wrong
         */
        void handle(@NonNull MimeMessageHelper messageHelper) throws Exception;
    }
}
