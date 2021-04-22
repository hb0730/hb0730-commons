package com.hb0730.commons.scm.git.clone.strategy;

import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.scm.git.builder.Git;
import com.hb0730.commons.scm.git.clone.GitCloneStrategy;
import com.hb0730.commons.scm.git.clone.builder.GitSshKeyClone;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.util.FS;

/**
 * Ssh 通过key的方式
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class SshKeyStrategy extends GitCloneStrategy {
    @Override
    public int order() {
        return 4;
    }

    @Override
    public boolean support(Git git) {
        return git instanceof GitSshKeyClone;
    }

    @Override
    protected CloneCommand getCommand() {
        final GitSshKeyClone git = (GitSshKeyClone) getGit();
        return getSuperCommand().setTransportConfigCallback(transport -> {
            if (transport instanceof SshTransport) {
                SshTransport sshTransport = (SshTransport) transport;
                sshTransport.setSshSessionFactory(sshSessionFactory(git.getKeyPath()));
            }
        });
    }

    private SshSessionFactory sshSessionFactory(String keyPath) {
        Validate.notBlank(keyPath, "private key path must be not null");
        return new JschConfigSessionFactory() {
            @Override
            protected JSch createDefaultJSch(FS fs) throws JSchException {
                JSch jsch = super.createDefaultJSch(fs);
                jsch.addIdentity(keyPath);
                return jsch;
            }
        };
    }
}
