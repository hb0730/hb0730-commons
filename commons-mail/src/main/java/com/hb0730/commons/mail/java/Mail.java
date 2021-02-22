package com.hb0730.commons.mail.java;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;
import com.hb0730.commons.mail.exceptions.MailException;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * 邮件发送客户端
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class Mail {
    private final MailAccount account;
    /**
     * 设置是否使用全局会话，默认为false
     */
    private boolean useGlobalSession = false;
    /**
     * 标题
     */
    private String subject;
    /**
     * 收件人列表
     */
    private String[] tos;
    /**
     * 抄送人列表（carbon copy）
     */
    private String[] ccs;
    /**
     * 密送人列表（blind carbon copy）
     */
    private String[] bccs;
    /**
     * 回复地址列表(reply-to)
     */
    private String[] reply;
    /**
     * 内容
     */
    private String content;
    /**
     * 是否为HTML
     */
    private boolean isHtml;
    /**
     * 正文、附件和图片的混合部分
     */
    private final Multipart multipart = new MimeMultipart();

    /**
     * 构造，使用全局邮件帐户
     */
    public Mail() {
        this(GlobalMailAccount.INSTANCE.getAccount());
    }

    /**
     * 创建邮件客户端
     *
     * @param account 邮件帐号
     * @return {@link Mail}
     */
    public static Mail create(MailAccount account) {
        return new Mail(account);
    }

    /**
     * 构造
     *
     * @param account 邮件帐户
     */
    public Mail(MailAccount account) {
        this.account = account;
    }

    public MailAccount getAccount() {
        return account;
    }

    /**
     * 是否使用全局会话
     *
     * @return 是否使用全局会话
     */
    public boolean isUseGlobalSession() {
        return useGlobalSession;
    }

    /**
     * 设置是否使用全局会话，默认为false
     *
     * @param useGlobalSession 是否使用全局会话，默认为false
     * @return this
     */
    public Mail setUseGlobalSession(boolean useGlobalSession) {
        this.useGlobalSession = useGlobalSession;
        return this;
    }

    /**
     * 设置收件人
     *
     * @param tos 收件人列表
     * @return this
     * @see #setTos(String...)
     */
    public Mail tos(String... tos) {
        return setTos(tos);
    }

    /**
     * 设置多个收件人
     *
     * @param tos 收件人列表
     * @return this
     */
    public Mail setTos(String... tos) {
        this.tos = tos;
        return this;
    }

    /**
     * 设置多个抄送人（carbon copy）
     *
     * @param ccs 抄送人列表
     * @return this
     * @see #setCcs(String...)
     */
    public Mail ccs(String... ccs) {
        return setCcs(ccs);
    }

    /**
     * 设置多个抄送人（carbon copy）
     *
     * @param ccs 抄送人列表
     * @return this
     */
    public Mail setCcs(String... ccs) {
        this.ccs = ccs;
        return this;
    }

    /**
     * 设置多个密送人（blind carbon copy）
     *
     * @param bccs 密送人列表
     * @return this
     * @see #bccs(String...)
     */
    public Mail bccs(String... bccs) {
        return setBccs(bccs);
    }

    /**
     * 设置多个密送人（blind carbon copy）
     *
     * @param bccs 密送人列表
     * @return this
     */
    public Mail setBccs(String... bccs) {
        this.bccs = bccs;
        return this;
    }

    /**
     * 设置多个回复地址(reply-to)
     *
     * @param reply 回复地址(reply-to)列表
     * @return this
     * @see #setReply(String...)
     */
    public Mail reply(String... reply) {
        return setReply(reply);
    }

    /**
     * 设置多个回复地址(reply-to)
     *
     * @param reply 回复地址(reply-to)列表
     * @return this
     */
    public Mail setReply(String... reply) {
        this.reply = reply;
        return this;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return this
     * @see #setTitle(String)
     */
    public Mail title(String title) {
        return setTitle(title);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     * @return this
     */
    public Mail setTitle(String title) {
        this.subject = title;
        return this;
    }

    /**
     * 设置字符集编码
     *
     * @param charset 字符集编码
     * @return this
     * @see MailAccount#charset(Charset)
     */
    public Mail setCharset(Charset charset) {
        this.account.charset(charset);
        return this;
    }

    /**
     * 设置字符集编码
     *
     * @param charset 字符集编码
     * @return this
     * @see #setCharset(Charset)
     */
    public Mail charset(Charset charset) {
        return setCharset(charset);
    }


    /**
     * 设置正文<br>
     * 正文可以是普通文本也可以是HTML（默认普通文本），可以通过调用{@link #setHtml(boolean)} 设置是否为HTML
     *
     * @param content 正文
     * @return this
     */
    public Mail setContent(String content) {
        this.content = content;
        return this;
    }

    /**
     * 设置是否是HTML
     *
     * @param isHtml 是否为HTML
     * @return this
     */
    public Mail setHtml(boolean isHtml) {
        this.isHtml = isHtml;
        return this;
    }

    /**
     * 设置正文
     *
     * @param content 正文内容
     * @param isHtml  是否为HTML
     * @return this
     */
    public Mail setContent(String content, boolean isHtml) {
        setContent(content);
        return setHtml(isHtml);
    }

    /**
     * 设置文件类型附件，文件可以是图片文件，此时自动设置cid（正文中引用图片），默认cid为文件名
     *
     * @param files 附件文件列表
     * @return this
     * @see #addAttachments(File...)
     */
    public Mail addAttachments(File... files) {
        if (ArrayUtils.isEmpty(files)) {
            return this;
        }

        final DataSource[] attachments = new DataSource[files.length];
        for (int i = 0; i < files.length; i++) {
            attachments[i] = new FileDataSource(files[i]);
        }
        return addAttachments(attachments);
    }

    /**
     * 增加附件或图片，附件使用{@link DataSource} 形式表示，可以使用{@link javax.activation.FileDataSource}包装文件表示文件附件
     *
     * @param attachments 附件列表
     * @return this
     */
    public Mail addAttachments(DataSource... attachments) {
        if (ArrayUtils.isNotEmpty(attachments)) {
            final Charset charset = this.account.getCharset();
            MimeBodyPart bodyPart;
            String nameEncoded;
            try {
                for (DataSource attachment : attachments) {
                    bodyPart = new MimeBodyPart();
                    nameEncoded = InternalMailUtil.encodeText(attachment.getName(), charset);
                    bodyPart.setDisposition(MimeBodyPart.ATTACHMENT);
                    bodyPart.setFileName(nameEncoded);
                    bodyPart.setDataHandler(new DataHandler(attachment));
                    if (attachment.getContentType().startsWith("image/")) {
                        // 图片附件，用于正文中引用图片
                        bodyPart.setContentID(nameEncoded);
                    }
                    this.multipart.addBodyPart(bodyPart);
                }
            } catch (MessagingException e) {
                throw new MailException(e);
            }
        }
        return this;
    }

    /**
     * 发送
     *
     * @return message-id
     * @throws MailException 邮件发送异常
     */
    public String send() {
        try {
            return doSend();
        } catch (MessagingException e) {
            if (e instanceof SendFailedException) {
                // 当地址无效时，显示更加详细的无效地址信息
                final Address[] invalidAddresses = ((SendFailedException) e).getInvalidAddresses();
                final String msg = String.format("Invalid Addresses: %s", ArrayUtils.toString(invalidAddresses));
                throw new MailException(msg, e);
            }
            throw new MailException(e);
        }
    }

    /**
     * 执行发送
     *
     * @return message-id
     * @throws MessagingException 发送异常
     */
    private String doSend() throws MessagingException {
        final MimeMessage mimeMessage = buildMsg();
        Transport.send(mimeMessage);
        return mimeMessage.getMessageID();
    }

    /**
     * 构建消息
     *
     * @return {@link MimeMessage}消息
     */
    private MimeMessage buildMsg() throws MessagingException {
        final Charset charset = this.account.getCharset();
        Session session = getSession();
        MimeMessage message = new MimeMessage(session);
        // 发件人
        String from = this.account.getFrom();
        if (StringUtils.isBlank(from)) {
            message.setFrom();
        } else {
            message.setFrom(getFromAddress());
        }
        // 标题
        message.setSubject(this.subject, charset.name());
        // 发送时间
        message.setSentDate(new Date());
        // 发送的内容
        message.setContent(buildContent(charset));
        // 收件人
        message.setRecipients(MimeMessage.RecipientType.TO, InternalMailUtil.parseAddressFromStrs(this.tos, charset));
        // 抄送人
        if (ArrayUtils.isNotEmpty(this.ccs)) {
            message.setRecipients(MimeMessage.RecipientType.CC, InternalMailUtil.parseAddressFromStrs(this.ccs, charset));
        }
        // 密送人
        if (ArrayUtils.isNotEmpty(this.bccs)) {
            message.setRecipients(MimeMessage.RecipientType.BCC, InternalMailUtil.parseAddressFromStrs(this.bccs, charset));
        }
        // 回复地址(reply-to)
        if (ArrayUtils.isNotEmpty(this.reply)) {
            message.setReplyTo(InternalMailUtil.parseAddressFromStrs(this.reply, charset));
        }
        return message;
    }

    /**
     * 构建邮件信息主体
     *
     * @param charset 编码
     * @return 邮件信息主体
     * @throws MessagingException 消息异常
     */
    private Multipart buildContent(Charset charset) throws MessagingException {
        // 正文
        final MimeBodyPart body = new MimeBodyPart();
        String value = String.format("text/%s; charset=%s", isHtml ? "html" : "plain", charset);
        body.setContent(content, value);
        this.multipart.addBodyPart(body);

        return this.multipart;
    }

    /**
     * 获取form地址
     *
     * @return {@link InternetAddress}
     */
    private InternetAddress getFromAddress() {
        try {

            return new InternetAddress(this.account.getUser(), this.account.getFrom(), this.account.getCharset().name());
        } catch (UnsupportedEncodingException e) {
            throw new MailException(e);
        }
    }

    /**
     * 获取默认邮件会话<br>
     * 如果为全局单例的会话，则全局只允许一个邮件帐号，否则每次发送邮件会新建一个新的会话
     *
     * @return 邮件会话 {@link Session}
     */
    private Session getSession() {
        Authenticator authenticator = null;
        if (this.account.isAuth()) {
            authenticator = new UserPassAuthenticator(this.account.getUser(), this.account.getPassword());
        }
        return isUseGlobalSession() ? Session.getDefaultInstance(this.account.getSmtpProps(), authenticator) : Session.getInstance(this.account.getSmtpProps(), authenticator);
    }

}
