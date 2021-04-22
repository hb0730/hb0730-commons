package com.hb0730.commons.scm.git.clone.builder;

import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.scm.git.builder.AbstractGitBuilder;
import com.hb0730.commons.scm.git.builder.Git;

import java.io.File;

/**
 * Git token clone
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitTokenClone extends Git {
    /**
     * user token
     */
    private String token;

    public GitTokenClone() {
    }

    public GitTokenClone(String remoteUrl, File localDirectory, String branch, String token) {
        super(remoteUrl, localDirectory, branch);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    /**
     * {@link GitTokenClone} builder
     *
     * @author bing_huang
     */
    public static class GitTokenCloneBuilder extends AbstractGitBuilder
            implements Builder<GitTokenClone> {
        /**
         * user token
         */
        private String token;

        public GitTokenCloneBuilder() {
        }

        public GitTokenCloneBuilder(String remoteUrl, File localDirectory, String branch, String token) {
            super(remoteUrl, localDirectory, branch);
            this.token = token;
        }

        /**
         * create {@link GitTokenCloneBuilder}
         *
         * @return {@link GitTokenCloneBuilder}
         */
        public static GitTokenCloneBuilder builder() {
            return new GitTokenCloneBuilder();
        }

        /**
         * create {@link GitTokenCloneBuilder}
         *
         * @param remoteUrl      远程目录
         * @param localDirectory 本地目录
         * @param branch         分支
         * @param token          token
         * @return {@link GitTokenCloneBuilder}
         */
        public static GitTokenCloneBuilder builder(String remoteUrl, File localDirectory, String branch, String token) {
            return new GitTokenCloneBuilder(remoteUrl, localDirectory, branch, token);
        }

        @Override
        public GitTokenClone build() {
            return new GitTokenClone(getRemoteUrl(), getLocalDirectory(), getBranch(), token);
        }

        @Override
        public GitTokenCloneBuilder remoteUrl(String remoteUrl) {
            setRemoteUrl(remoteUrl);
            return this;
        }

        @Override
        public GitTokenCloneBuilder localDirectory(File localDirectory) {
            setLocalDirectory(localDirectory);
            return this;
        }

        @Override
        public GitTokenCloneBuilder branch(String branch) {
            setBranch(branch);
            return this;
        }

        public GitTokenCloneBuilder token(String token) {
            this.token = token;
            return this;
        }
    }
}
