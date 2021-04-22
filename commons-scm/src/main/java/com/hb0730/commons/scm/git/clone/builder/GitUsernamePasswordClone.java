package com.hb0730.commons.scm.git.clone.builder;

import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.scm.git.builder.AbstractGitBuilder;
import com.hb0730.commons.scm.git.builder.Git;

import java.io.File;

/**
 * username/password clone
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitUsernamePasswordClone extends Git {
    /**
     * username
     */
    private String username;
    /**
     * password
     */
    private String password;

    public GitUsernamePasswordClone() {
    }

    public GitUsernamePasswordClone(String remoteUrl, File localDirectory, String branch,
                                    String username, String password) {
        super(remoteUrl, localDirectory, branch);
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * {@link GitUsernamePasswordClone} builder
     *
     * @since 2.1.2
     */
    public static class GitUsernamePasswordCloneBuilder extends AbstractGitBuilder
            implements Builder<GitUsernamePasswordClone> {
        /**
         * username
         */
        private String username;
        /**
         * password
         */
        private String password;


        public GitUsernamePasswordCloneBuilder() {
        }

        public GitUsernamePasswordCloneBuilder(String remoteUrl, File localDirectory,
                                               String branch, String username, String password) {
            super(remoteUrl, localDirectory, branch);
            this.username = username;
            this.password = password;
        }

        /**
         * create {@link GitUsernamePasswordCloneBuilder}
         *
         * @return {@link GitUsernamePasswordCloneBuilder}
         */
        public static GitUsernamePasswordCloneBuilder builder() {
            return new GitUsernamePasswordCloneBuilder();
        }

        /**
         * create {@link GitUsernamePasswordCloneBuilder}
         *
         * @param remoteUrl      远程目录
         * @param localDirectory 本地目录
         * @param branch         分支
         * @param username       username
         * @param password       password
         * @return {@link GitUsernamePasswordCloneBuilder}
         */
        public static GitUsernamePasswordCloneBuilder builder(String remoteUrl, File localDirectory, String branch, String username, String password) {
            return new GitUsernamePasswordCloneBuilder(remoteUrl, localDirectory, branch, username, password);
        }

        @Override
        public GitUsernamePasswordClone build() {
            return new GitUsernamePasswordClone(getRemoteUrl(), getLocalDirectory(), getBranch(), username, password);
        }

        @Override
        public GitUsernamePasswordCloneBuilder remoteUrl(String remoteUrl) {
            setRemoteUrl(remoteUrl);
            return this;
        }

        @Override
        public GitUsernamePasswordCloneBuilder localDirectory(File localDirectory) {
            setLocalDirectory(localDirectory);
            return this;
        }

        @Override
        public GitUsernamePasswordCloneBuilder branch(String branch) {
            setBranch(branch);
            return this;
        }

        public GitUsernamePasswordCloneBuilder username(String username) {
            this.username = username;
            return this;
        }

        public GitUsernamePasswordCloneBuilder password(String password) {
            this.password = password;
            return this;
        }
    }
}
