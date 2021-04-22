package com.hb0730.commons.scm.git.builder;

import com.hb0730.commons.lang.builder.Builder;

import java.io.File;

/**
 * {@link Git}Builder
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitBuilder extends AbstractGitBuilder implements Builder<Git> {
    public GitBuilder() {
    }

    public GitBuilder(String remoteUrl, File localDirectory, String branch) {
        super(remoteUrl, localDirectory, branch);
    }

    /**
     * create {@link GitBuilder}
     *
     * @return {@link GitBuilder}
     */
    public static GitBuilder builder() {
        return new GitBuilder();
    }

    /**
     * create {@link GitBuilder}
     *
     * @param remoteUrl      远程路径
     * @param localDirectory 本地目录
     * @param branch         分支
     * @return {@link GitBuilder}
     */
    public static GitBuilder builder(String remoteUrl, File localDirectory, String branch) {
        return new GitBuilder(remoteUrl, localDirectory, branch);
    }

    @Override
    public Git build() {
        return new Git(getRemoteUrl(), getLocalDirectory(), getBranch());
    }

    @Override
    public GitBuilder remoteUrl(String remoteUrl) {
        setRemoteUrl(remoteUrl);
        return this;
    }

    @Override
    public GitBuilder localDirectory(File localDirectory) {
        setLocalDirectory(localDirectory);
        return this;
    }

    @Override
    public GitBuilder branch(String branch) {
        setBranch(branch);
        return this;
    }
}
