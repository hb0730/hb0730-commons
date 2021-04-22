package com.hb0730.commons.scm.git.builder;

import java.io.File;

/**
 * Abstract GitBuilder
 *
 * @author bing_huang
 * @since 2.1.2
 */
public abstract class AbstractGitBuilder {
    /**
     * git 远程路径
     */
    private String remoteUrl;
    /**
     * git 本地目录
     */
    private File localDirectory;
    /**
     * 分支
     */
    private String branch = "master";

    public AbstractGitBuilder() {
    }

    public AbstractGitBuilder(String remoteUrl, File localDirectory, String branch) {
        this.remoteUrl = remoteUrl;
        this.localDirectory = localDirectory;
        this.branch = branch;
    }

    /**
     * builder remote Url
     *
     * @param remoteUrl remote url
     * @return {@link AbstractGitBuilder}
     */
    public abstract AbstractGitBuilder remoteUrl(String remoteUrl);

    /**
     * builder local directory
     *
     * @param localDirectory local directory
     * @return {@link AbstractGitBuilder}
     */
    public abstract AbstractGitBuilder localDirectory(File localDirectory);

    /**
     * repository branch
     *
     * @param branch repository branch
     * @return {@link AbstractGitBuilder}
     */
    public abstract AbstractGitBuilder branch(String branch);

    public String getRemoteUrl() {
        return remoteUrl;
    }

    public void setRemoteUrl(String remoteUrl) {
        this.remoteUrl = remoteUrl;
    }

    public File getLocalDirectory() {
        return localDirectory;
    }

    public void setLocalDirectory(File localDirectory) {
        this.localDirectory = localDirectory;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }
}
