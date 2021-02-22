package com.hb0730.commons.mail.java;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.collection.ArrayUtils;
import com.hb0730.commons.mail.exceptions.MailException;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 邮件内部工具类
 *
 * @author bing_huang
 * @since 2.1.0
 */
public class InternalMailUtil {
    /**
     * 将多个字符串邮件地址转为{@link InternetAddress}列表<br>
     * 单个字符串地址可以是多个地址合并的字符串
     *
     * @param addrStrs 地址数组
     * @param charset  编码（主要用于中文用户名的编码）
     * @return 地址数组
     */
    public static InternetAddress[] parseAddressFromStrs(String[] addrStrs, Charset charset) {
        final List<InternetAddress> resultList = new ArrayList<>(addrStrs.length);
        InternetAddress[] addrs;
        for (String addrStr : addrStrs) {
            addrs = parseAddress(addrStr, charset);
            if (ArrayUtils.isNotEmpty(addrs)) {
                Collections.addAll(resultList, addrs);
            }
        }
        return resultList.toArray(new InternetAddress[0]);
    }

    /**
     * 将一个地址字符串解析为地址
     *
     * @param address 地址字符串
     * @param charset 编码
     * @return 地址
     */
    public static InternetAddress parseFirstAddress(String address, Charset charset) {
        InternetAddress[] addresses = parseAddress(address, charset);
        if (StringUtils.isEmpty(address)) {
            try {
                return new InternetAddress(address);
            } catch (AddressException e) {
                throw new MailException(e);
            }
        }
        return addresses[0];
    }

    /**
     * 将一个地址字符串解析为地址多个地址
     *
     * @param address 地址字符串
     * @param charset 编码
     * @return 地址列表
     */
    public static InternetAddress[] parseAddress(String address, Charset charset) {
        InternetAddress[] parsed = null;
        try {
            parsed = InternetAddress.parse(address);
        } catch (AddressException e) {
            throw new MailException(e);
        }
        if (ArrayUtils.isNotEmpty(parsed)) {
            for (InternetAddress internetAddress : parsed) {
                try {
                    internetAddress.setPersonal(internetAddress.getPersonal(), charset.name());
                } catch (UnsupportedEncodingException e) {
                    throw new MailException(e);
                }
            }
        }
        return parsed;
    }

    /**
     * 编码中文字符<br>
     * 编码失败返回原字符串
     *
     * @param text    被编码的文本
     * @param charset 编码
     * @return 编码后的结果
     */
    public static String encodeText(String text, Charset charset) {
        try {
            return MimeUtility.encodeText(text, charset.name(), null);
        } catch (UnsupportedEncodingException e) {
            // ignore
        }
        return text;
    }
}
