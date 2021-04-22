package com.hb0730.commons.scm.git.clone.builder;

import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.scm.git.builder.AbstractGitBuilder;
import com.hb0730.commons.scm.git.builder.Git;

import java.io.File;

/**
 * ssh password clone
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitSshPasswordClone extends Git {
    /**
     * password
     */
    private String password;

    public GitSshPasswordClone() {
    }

    public GitSshPasswordClone(String remoteUrl, File localDirectory,
                               String branch, String password) {
        super(remoteUrl, localDirectory, branch);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@link GitSshPasswordClone} builder
     *
     * @author bing_huang
     * @since 2.1.2
     */
    public static class GitSshPasswordCloneBuilder extends AbstractGitBuilder
            implements Builder<GitSshPasswordClone> {

        /**
         * 密码
         */
        private String password;

        public GitSshPasswordCloneBuilder() {
        }

        public GitSshPasswordCloneBuilder(String remoteUrl, File localDirectory,
                                          String branch, String password) {
            super(remoteUrl, localDirectory, branch);
            this.password = password;
        }

        /**
         * create {@link GitSshPasswordCloneBuilder}
         *
         * @return {@link GitSshPasswordCloneBuilder}
         */
        public static GitSshPasswordCloneBuilder builder() {
            return new GitSshPasswordCloneBuilder();
        }

        /**
         * create {@link GitSshPasswordCloneBuilder}
         *
         * @param remoteUrl      远程目录
         * @param localDirectory 本地目录
         * @param branch         分支
         * @param password       password
         * @return {@link GitSshPasswordCloneBuilder}
         */
        public static GitSshPasswordCloneBuilder builder(String remoteUrl, File localDirectory,
                                                         String branch, String password) {
            return new GitSshPasswordCloneBuilder(remoteUrl, localDirectory, branch, password);
        }

        @Override
        public GitSshPasswordClone build() {
            return new GitSshPasswordClone(getRemoteUrl(), getLocalDirectory(), getBranch(), password);
        }

        @Override
        public GitSshPasswordCloneBuilder remoteUrl(String remoteUrl) {
            setRemoteUrl(remoteUrl);
            return this;
        }

        @Override
        public GitSshPasswordCloneBuilder localDirectory(File localDirectory) {
            setLocalDirectory(localDirectory);
            return this;
        }

        @Override
        public GitSshPasswordCloneBuilder branch(String branch) {
            setBranch(branch);
            return this;
        }

        public GitSshPasswordCloneBuilder password(String password) {
            this.password = password;
            return this;
        }
    }
}
