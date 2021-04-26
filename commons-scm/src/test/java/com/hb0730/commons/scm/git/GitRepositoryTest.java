package com.hb0730.commons.scm.git;

import com.hb0730.commons.lang.io.FileUtils;
import com.jcraft.jsch.Session;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.JschConfigSessionFactory;
import org.eclipse.jgit.transport.OpenSshConfig;
import org.eclipse.jgit.transport.SshSessionFactory;
import org.eclipse.jgit.transport.SshTransport;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

@Slf4j
public class GitRepositoryTest extends GitTest {
    File file = null;
    Git git = null;

    @Before
    public void before() {
        try {
            FileUtils.createOrExistsDir("E:/temp/TestGitRepositorygithub");
            file = FileUtils.getFile("E:/temp/TestGitRepositorygithub");
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @After
    public void after() {
        if (null != git) {
            git.close();
        }
    }


    /**
     * 私有https
     *
     * @throws GitAPIException
     */
    @Test
    public void privateHttpsTest() throws GitAPIException {
        try {
            CloneCommand command = Git.cloneRepository();
            PrivateHttps https = privateHttps();
            git = command.setURI(https.remoteUrl())
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(https.username(), https.password()))
                    .setDirectory(file)
                    .setBranch(https.branch())
                    .call();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 私有ssh
     *
     * @throws GitAPIException
     */
    @Test
    public void privateSShTest() throws GitAPIException {
        try {
            PrivateSsh ssh = privateSsh();
            git = Git.cloneRepository()
                    .setURI(ssh.remoteUrl())
                    .setBranch(ssh.branch())
                    .setDirectory(file)
                    .setTransportConfigCallback(transport -> {
                        if (transport instanceof SshTransport) {
                            SshTransport sshTransport = (SshTransport) transport;
                            sshTransport.setSshSessionFactory(sshSessionFactory(ssh.password()));
                        }
                    })
                    .call();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

    }

    /**
     * 私有http
     *
     * @throws GitAPIException
     */
    @Test
    public void privateHttpTest() throws GitAPIException {
        try {
            PrivateHttp http = privateHttp();
            git = Git.cloneRepository()
                    .setURI(http.remoteUrl())
                    .setBranch(http.branch())
                    .setDirectory(file)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider(http.username(), http.password()))
                    .call();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @SneakyThrows
    @Test
    public void privateHttpTokenTest() {
        try {
            PrivateHttpToken httpToken = privateHttpToken();
            git = Git.cloneRepository()
                    .setURI(httpToken.remoteUrl())
                    .setBranch(httpToken.branch())
                    .setDirectory(file)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", httpToken.token()))
                    .call();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    @Test
    public void privateSshTokenTest() throws GitAPIException {
        try {
            PrivateSshToken sshToken = privateSshToken();
            git = Git.cloneRepository()
                    .setURI(sshToken.remoteUrl())
                    .setBranch(sshToken.branch())
                    .setDirectory(file)
                    .setCredentialsProvider(new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", sshToken.token()))
                    .call();
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
    }

    private SshSessionFactory sshSessionFactory(String password) {
        return new JschConfigSessionFactory() {
            @Override
            protected void configure(OpenSshConfig.Host hc, Session session) {
                session.setPassword(password);
            }
        };
    }
}
