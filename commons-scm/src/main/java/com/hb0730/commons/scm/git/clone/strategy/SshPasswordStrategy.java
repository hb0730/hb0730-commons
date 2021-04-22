package com.hb0730.commons.scm.git.clone.strategy;

import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.scm.git.builder.Git;
import com.hb0730.commons.scm.git.clone.GitCloneStrategy;
import com.hb0730.commons.scm.git.clone.builder.GitSshPasswordClone;
import com.jcraft.jsch.Session;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;

/**
 * SSH方式通过password clone
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class SshPasswordStrategy extends GitCloneStrategy {

    @Override
    public int order() {
        return 1;
    }

    @Override
    public boolean support(Git git) {
        return git instanceof GitSshPasswordClone;
    }

    @Override
    protected CloneCommand getCommand() {
        final GitSshPasswordClone git = (GitSshPasswordClone) getGit();
        return getSuperCommand().setTransportConfigCallback(transport -> {
            if (transport instanceof SshTransport) {
                SshTransport sshTransport = (SshTransport) transport;
                sshTransport.setSshSessionFactory(sshSessionFactory(git.getPassword()));
            }
        });
    }

    private SshSessionFactory sshSessionFactory(String password) {
        Validate.notBlank("password must be not null");
        return new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host hc, Session session) {
                session.setPassword(password);
            }
        };
    }

}
