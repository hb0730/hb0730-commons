package com.hb0730.commons.spring;

import com.hb0730.commons.lang.convert.ConvertUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet工具类
 *
 * @author bing_huang
 * @since 1.0.0
 */
public class ServletUtils {
    /**
     * 获取string类型参数
     *
     * @param name 请求参数名称
     * @return value
     */
    public static String getParameter(String name) {
        return getRequest().getParameter(name);
    }

    /**
     * 获取string参数
     *
     * @param name         请求名称
     * @param defaultValue 默认参数
     * @return 如果请求参数为空返回默认参数
     */
    public static String getParameter(String name, String defaultValue) {
        return ConvertUtils.toStr(getRequest().getParameter(name), defaultValue);
    }

    /**
     * 获取int类型参数
     *
     * @param name 请求参数名
     * @return value
     */
    public static Integer getParameterToInt(String name) {
        return ConvertUtils.toInt(getRequest().getParameter(name));
    }

    /**
     * 获取int类型参数
     *
     * @param name         请求参数名
     * @param defaultValue 默认参数
     * @return 如果Integer为空则返回默认值
     */
    public static Integer getParameterToInt(String name, Integer defaultValue) {
        return ConvertUtils.toInt(getRequest().getParameter(name), defaultValue);
    }


    /**
     * 获取当前session
     *
     * @return {@link HttpSession}
     */
    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 获取当前Request
     *
     * @return {@link HttpServletRequest}
     */
    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    /**
     * 获取 response
     *
     * @return {@link HttpServletResponse}
     */
    public static HttpServletResponse getResponse() {
        return getRequestAttributes().getResponse();
    }

    /**
     * 获取spring web ServletRequestAttributes
     *
     * @return {@link ServletRequestAttributes}
     */
    public static ServletRequestAttributes getRequestAttributes() {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }

    /**
     * 将字符串渲染到客户端
     *
     * @param response response
     * @param string   待渲染字符串
     */
    public static void renderString(HttpServletResponse response, String string) throws IOException {
        response.setStatus(200);
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(string);
    }
}
