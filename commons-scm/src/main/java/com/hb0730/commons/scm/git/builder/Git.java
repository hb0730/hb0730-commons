package com.hb0730.commons.scm.git.builder;

import com.hb0730.commons.lang.StringUtils;

import java.io.File;

/**
 * git必要参数
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class Git {
    public static final String DEFAULT_BRANCH = "master";
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
    private String branch = DEFAULT_BRANCH;

    public Git() {
    }

    public Git(String remoteUrl, File localDirectory, String branch) {
        this.remoteUrl = remoteUrl;
        this.localDirectory = localDirectory;
        this.branch = StringUtils.isBlank(branch) ? DEFAULT_BRANCH : branch;
    }

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
        this.branch = StringUtils.isBlank(branch) ? DEFAULT_BRANCH : branch;
    }
}
