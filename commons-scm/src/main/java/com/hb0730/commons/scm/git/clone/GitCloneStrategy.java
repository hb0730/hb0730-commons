package com.hb0730.commons.scm.git.clone;

import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.scm.git.builder.Git;
import org.eclipse.jgit.api.CloneCommand;

/**
 * Git 根据不同协议执行不同参数
 *
 * @author bing_huang
 * @since 2.1.2
 */
public abstract class GitCloneStrategy implements GitCloneCommand {
    protected static final String SSH = "ssh";
    protected CloneCommand command;
    protected Git git;

    public GitCloneStrategy() {
    }

    /**
     * 有参构造
     *
     * @param command {@link CloneCommand}
     * @param git     {@link Git}
     */
    public GitCloneStrategy(CloneCommand command, Git git) {
        this.command = command;
        this.git = git;
    }

    /**
     * 获取{@link CloneCommand}
     *
     * @return {@link CloneCommand},可能为空
     */
    protected abstract CloneCommand getCommand();

    @Override
    public CloneCommand doCommand() {
        return getCommand();
    }

    public void setCommand(CloneCommand command) {
        this.command = command;
    }

    public void setGit(Git git) {
        this.git = git;
    }

    public CloneCommand getSuperCommand() {
        Validate.notNull(command, "Please set CloneCommand");
        return command;
    }

    public Git getGit() {
        Validate.notNull(git, "Please set Git");
        return git;
    }
}
