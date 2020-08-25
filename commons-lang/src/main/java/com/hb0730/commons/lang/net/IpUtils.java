package com.hb0730.commons.lang.net;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.constants.PunctuationConst;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.regex.Pattern;

/**
 * ip 工具
 *
 * @author bing_huang
 * @since 2.0.0
 */
public class IpUtils {
    /**
     * 默认最大端口，65535
     */
    public static final int PORT_RANGE_MAX = 0xFFFF;

    private static final String ANYHOST = "0.0.0.0";
    private static final String LOCALHOST = "127.0.0.1";
    public static final Pattern IP_PATTERN = Pattern.compile("\\d{1,3}(\\.\\d{1,3}){3,5}$");

    private static volatile String LOCAL_ADDRESS = null;

    /**
     * * 从多级反向代理中获得第一个非unknown IP地址
     *
     * @param ip 获得的IP地址
     * @return 第一个非unknown IP地址
     */
    public static String getIp(String ip) {
        // 多级反向代理检测
        if (ip != null && ip.indexOf(PunctuationConst.COMMA) > 0) {
            final String[] ips = ip.trim().split(PunctuationConst.COMMA);
            for (String subIp : ips) {
                if (!isUnknown(subIp)) {
                    ip = subIp;
                    break;
                }
            }
        }
        return ip;
    }

    /**
     * 检测给定字符串是否为未知，多用于检测HTTP请求相关<br>
     *
     * @param checkString 被检测的字符串
     * @return 是否未知
     */
    public static boolean isUnknown(String checkString) {
        return StringUtils.isBlank(checkString) || "unknown".equalsIgnoreCase(checkString);
    }

    /**
     * 获取ip
     *
     * @return 有效的ip
     */
    public static String getIp() {
        return getAddress();
    }

    /**
     * 构造一个ip:port
     *
     * @param port 端口
     * @return String ip:端口
     */
    public static String getIpPort(int port) {
        String ip = getIp();
        if (ip == null) {
            return null;
        }
        return ip.concat(":").concat(String.valueOf(port));
    }

    /**
     * 获取地址
     *
     * @return 有效地址
     */
    public static String getAddress() {
        if (LOCAL_ADDRESS != null) {
            return LOCAL_ADDRESS;
        }
        InetAddress address = getFirstValidAddress();
        LOCAL_ADDRESS = address == null ? null : address.getHostAddress();
        return LOCAL_ADDRESS;
    }

    /**
     * 是否为有效的端口<br>
     * 此方法并不检查端口是否被占用
     *
     * @param port 端口号
     * @return 是否有效
     */
    public static boolean isValidPort(int port) {
        // 有效端口是0～65535
        return port >= 0 && port <= PORT_RANGE_MAX;
    }

    /**
     * 校验地址是否有效,非127.0.0.1,0.0.0.0符合ip规范
     *
     * @param address 地址
     * @return 是否有效
     */
    private static boolean isValidAddress(InetAddress address) {
        if (address == null ||
                address.isLoopbackAddress() ||
                address.isLinkLocalAddress()) {
            return false;
        }
        String hostAddress = address.getHostAddress();
        return (null != hostAddress
                && !ANYHOST.equals(hostAddress)
                && !LOCALHOST.equals(hostAddress)
                && IP_PATTERN.matcher(hostAddress).matches());
    }

    /**
     * 获取第一个有效的地址
     *
     * @return 有效地址 {@link InetAddress}
     */
    private static InetAddress getFirstValidAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            if (null != interfaces) {
                while (interfaces.hasMoreElements()) {
                    NetworkInterface network = interfaces.nextElement();
                    Enumeration<InetAddress> addresses = network.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        InetAddress inetAddress = addresses.nextElement();
                        if (isValidAddress(inetAddress)) {
                            return inetAddress;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        try {
            InetAddress localHost = InetAddress.getLocalHost();
            if (isValidAddress(localHost)) {
                return localHost;
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }
}
