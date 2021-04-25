package com.hb0730.commons.lang.runtime;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.lang.constants.PunctuationConst;
import com.hb0730.commons.lang.io.IORuntimeException;
import com.hb0730.commons.lang.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 系统运行时工具类，用于执行系统命令的工具
 *
 * @author bing_huang
 * @since 2.1.3
 */
public class RuntimeUtils {
    /**
     * 执行系统命令，使用系统默认编码
     *
     * @param cmds 命令列表，每个元素代表一条命令
     * @return 执行结果
     * @throws IORuntimeException IO异常
     */
    public static String exec(String... cmds) throws IORuntimeException {
        return exec(Charset.defaultCharset(), cmds);
    }

    /**
     * 执行系统命令，使用系统默认编码
     *
     * @param charset 编码
     * @param cmds    命令列表，每个元素代表一条命令
     * @return 执行结果
     * @throws IORuntimeException IO异常
     */
    public static String exec(Charset charset, String... cmds) {
        return getResult(execProcess(cmds), charset);
    }


    /**
     * 执行命令
     *
     * @param cmds 命令,自动将{@link PunctuationConst#BLANK}切割
     * @return {@link Process}
     */
    public static Process execProcess(String... cmds) {
        return execProcess((File) null, cmds);
    }

    /**
     * 执行命令
     *
     * @param cmds 命令,自动将{@link PunctuationConst#BLANK}切割
     * @param dir  执行命令所在目录（用于相对路径命令执行），null表示使用当前进程执行的目录
     * @return {@link Process}
     */
    public static Process execProcess(File dir, String... cmds) {
        Validate.notEmpty(cmds, "Commands must be not Empty");
        if (cmds.length == 1) {
            final String cmd = cmds[0];
            if (StringUtils.isBlank(cmd)) {
                Validate.notEmpty(cmds, "Commands must be not Empty");
            }
            cmds = cmd.split(PunctuationConst.BLANK);
        }
        try {
            return new ProcessBuilder(cmds)
                    .directory(dir)
                    .redirectErrorStream(true)
                    .start();
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 执行命令<br>
     * 命令带参数时参数可作为其中一个参数，也可以将命令和参数组合为一个字符串传入
     *
     * @param envp 环境变量参数，传入形式为key=value，null表示继承系统环境变量
     * @param cmds 命令
     * @return {@link Process}
     */
    public static Process execProcess(String[] envp, String... cmds) {
        return execProcess(envp, null, cmds);
    }

    /**
     * 执行命令<br>
     * 命令带参数时参数可作为其中一个参数，也可以将命令和参数组合为一个字符串传入
     *
     * @param envs 环境变量参数，传入形式为key=value，null表示继承系统环境变量
     * @param dir  执行命令所在目录（用于相对路径命令执行），null表示使用当前进程执行的目录
     * @param cmds 命令
     * @return {@link Process}
     */
    public static Process execProcess(String[] envs, File dir, String... cmds) {
        Validate.notEmpty(cmds, "Commands must be not Empty");
        if (cmds.length == 1) {
            final String cmd = cmds[0];
            if (StringUtils.isBlank(cmd)) {
                Validate.notEmpty(cmds, "Commands must be not Empty");
            }
            cmds = cmd.split(PunctuationConst.BLANK);
        }
        try {
            return Runtime.getRuntime().exec(cmds, envs, dir);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        }
    }

    /**
     * 获取命令执行结果，获取后销毁进程,系统默认编码
     *
     * @param process {@link Process} 进程
     * @return 命令执行结果
     */
    public static String getResult(Process process) {
        return getResult(process, Charset.defaultCharset());
    }

    /**
     * 获取命令执行结果，获取后销毁进程
     *
     * @param process {@link Process} 进程
     * @param charset 编码
     * @return 命令执行结果
     */
    public static String getResult(Process process, Charset charset) {
        if (null == process) {
            return PunctuationConst.BLANK;
        }
        InputStream in = null;
        try {
            in = process.getInputStream();
            return IOUtils.read(in, charset);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
            destroy(process);
        }
    }

    /**
     * 获取命令执行异常结果，使用系统默认编码，，获取后销毁进程
     *
     * @param process {@link Process} 进程
     * @return 命令执行结果列表
     */
    public static String getErrorResult(Process process) {
        return getErrorResult(process, Charset.defaultCharset());
    }

    /**
     * 获取命令执行异常结果，获取后销毁进程
     *
     * @param process {@link Process} 进程
     * @param charset 编码
     * @return 命令执行结果列表
     */
    public static String getErrorResult(Process process, Charset charset) {
        InputStream in = null;
        try {
            in = process.getErrorStream();
            return IOUtils.read(in, charset);
        } catch (IOException e) {
            throw new IORuntimeException(e);
        } finally {
            IOUtils.closeQuietly(in);
            destroy(process);
        }
    }


    /**
     * 销毁进程
     *
     * @param process 进程
     * @see Process#destroy()
     */
    public static void destroy(Process process) {
        if (null != process) {
            process.destroy();
        }
    }

    /**
     * 获得JVM可用的处理器数量（一般为CPU核心数）
     *
     * @return 可用的处理器数量
     */
    public static int getProcessorCount() {
        return Runtime.getRuntime().availableProcessors();
    }

    /**
     * 获得JVM中剩余的内存数，单位byte
     *
     * @return JVM中剩余的内存数，单位byte
     */
    public static long getFreeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 获得JVM已经从系统中获取到的总共的内存数，单位byte
     *
     * @return JVM中剩余的内存数，单位byte
     */
    public static long getTotalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 获得JVM中可以从系统中获取的最大的内存数，单位byte，以-Xmx参数为准
     *
     * @return JVM中剩余的内存数，单位byte
     */
    public static long getMaxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 获得JVM最大可用内存，计算方法为：<br>
     * 最大内存-总内存+剩余内存
     *
     * @return 最大可用内存
     */
    public static long getUsableMemory() {
        return getMaxMemory() - getTotalMemory() + getFreeMemory();
    }
}
