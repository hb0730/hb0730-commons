package com.hb0730.commons.scm.git.clone.builder;

import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.scm.git.builder.AbstractGitBuilder;
import com.hb0730.commons.scm.git.builder.Git;

import java.io.File;

/**
 * SSH private Key clone
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitSshKeyClone extends Git {
    /**
     * private key path
     */
    private String keyPath;

    public GitSshKeyClone() {
    }

    public GitSshKeyClone(String remoteUrl, File localDirectory, String branch, String keyPath) {
        super(remoteUrl, localDirectory, branch);
        this.keyPath = keyPath;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    /**
     * {@link GitSshKeyClone} builder
     *
     * @author bing_huang
     */
    public static class GitSshKeyCloneBuilder extends AbstractGitBuilder
            implements Builder<GitSshKeyClone> {
        /**
         * private key path
         */
        private String keyPath;


        public GitSshKeyCloneBuilder() {
        }

        public GitSshKeyCloneBuilder(String remoteUrl, File localDirectory, String branch, String keyPath) {
            super(remoteUrl, localDirectory, branch);
            this.keyPath = keyPath;
        }

        /**
         * create {@link GitSshKeyCloneBuilder}
         *
         * @return {@link GitSshKeyCloneBuilder}
         */
        public static GitSshKeyCloneBuilder builder() {
            return new GitSshKeyCloneBuilder();
        }

        /**
         * create {@link GitSshKeyCloneBuilder}
         *
         * @param remoteUrl      远程路径
         * @param localDirectory 本地目录
         * @param branch         分支
         * @param keyPath        private key path
         * @return {@link GitSshKeyCloneBuilder}
         */
        public static GitSshKeyCloneBuilder builder(String remoteUrl,
                                                    File localDirectory,
                                                    String branch,
                                                    String keyPath) {
            return new GitSshKeyCloneBuilder(remoteUrl, localDirectory, branch, keyPath);
        }

        @Override
        public GitSshKeyClone build() {
            return new GitSshKeyClone(getRemoteUrl(), getLocalDirectory(), getBranch(), keyPath);
        }

        @Override
        public GitSshKeyCloneBuilder remoteUrl(String remoteUrl) {
            setRemoteUrl(remoteUrl);
            return this;
        }

        @Override
        public GitSshKeyCloneBuilder localDirectory(File localDirectory) {
            setLocalDirectory(localDirectory);
            return this;
        }

        @Override
        public GitSshKeyCloneBuilder branch(String branch) {
            setBranch(branch);
            return this;
        }

        public GitSshKeyCloneBuilder keyPath(String keyPath) {
            this.keyPath = keyPath;
            return this;
        }
    }
}
