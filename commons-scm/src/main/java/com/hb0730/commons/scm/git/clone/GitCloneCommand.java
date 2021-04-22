package com.hb0730.commons.scm.git.clone;

import com.hb0730.commons.scm.git.builder.Git;
import org.eclipse.jgit.api.CloneCommand;

/**
 * 获取{@link GitCloneCommand}<br>
 * 由于<code>ssh</code>,<code>http</code>,<code>username/password</code>等使用的不同方式
 *
 * @author bing_huang
 * @since 2.1.2
 */
public interface GitCloneCommand {
    /**
     * 顺序
     *
     * @return 顺序
     * @see #support(Git)
     */
    int order();

    /**
     * 执行执行
     *
     * @return {@link CloneCommand}
     */
    CloneCommand doCommand();

    /**
     * 是否支持
     *
     * @param git {@link Git}
     * @return 是否支持
     */
    boolean support(Git git);
}
