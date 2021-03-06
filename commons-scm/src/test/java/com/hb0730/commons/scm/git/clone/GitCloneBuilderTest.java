package com.hb0730.commons.scm.git.clone;

import com.hb0730.commons.lang.io.FileUtils;
import com.hb0730.commons.scm.git.GitTest;
import com.hb0730.commons.scm.git.builder.GitBuilder;
import com.hb0730.commons.scm.git.clone.builder.GitCloneBuilder;
import com.hb0730.commons.scm.git.clone.builder.GitSshKeyClone;
import com.hb0730.commons.scm.git.clone.builder.GitUsernamePasswordClone;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

public class GitCloneBuilderTest extends GitTest {
    File file = null;
    Git git = null;

    @Before
    public void before() {
        FileUtils.deleteDir("E:/temp/TestGitRepositorygithub");
        FileUtils.createOrExistsDir("E:/temp/TestGitRepositorygithub");
        file = FileUtils.getFile("E:/temp/TestGitRepositorygithub");
    }

    @After
    public void after() {
        if (null != git) {
            git.close();
        }
    }

    @Test
    public void buildPublicTest() throws GitAPIException {
        CloneCommand command = GitCloneBuilder.builder(
                GitBuilder.builder()
                        .branch("master")
                        .remoteUrl("https://github.com/hb0730/hb0730-commons.git")
                        .localDirectory(file)
        ).build();
        git = command.call();
    }

    @Test
    public void buildUsernamePasswordTest() throws GitAPIException {
        PrivateHttps https = privateHttps();
        CloneCommand command = GitCloneBuilder.builder(
                GitUsernamePasswordClone.GitUsernamePasswordCloneBuilder.builder()
                        .remoteUrl(https.remoteUrl())
                        .branch(https.branch())
                        .localDirectory(file)
                        .username(https.username())
                        .password(https.password())
        ).build();
        git = command.call();
    }

    @Test
    public void buildKeyTest() throws GitAPIException {
        PrivateSsh ssh = privateSsh();
        CloneCommand command = GitCloneBuilder.builder(
                GitSshKeyClone.GitSshKeyCloneBuilder.builder()
                        .remoteUrl(ssh.remoteUrl())
                        .branch(ssh.branch())
                        .localDirectory(file)
                        .keyPath("~/.ssh/id_rsa")
        ).build();
        git = command.call();
    }

}
