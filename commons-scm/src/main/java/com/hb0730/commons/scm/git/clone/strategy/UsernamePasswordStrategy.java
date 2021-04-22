package com.hb0730.commons.scm.git.clone.strategy;

import com.hb0730.commons.lang.Validate;
import com.hb0730.commons.scm.git.builder.Git;
import com.hb0730.commons.scm.git.clone.GitCloneCommand;
import com.hb0730.commons.scm.git.clone.GitCloneStrategy;
import com.hb0730.commons.scm.git.clone.builder.GitUsernamePasswordClone;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * 通过username/password进行克隆
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class UsernamePasswordStrategy extends GitCloneStrategy implements GitCloneCommand {
    @Override
    protected CloneCommand getCommand() {
        final GitUsernamePasswordClone git = (GitUsernamePasswordClone) getGit();
        String username = git.getUsername();
        String password = git.getPassword();
        Validate.notBlank(username, "username must be not null");
        Validate.notBlank(password, "password must be not null");
        return getSuperCommand().setCredentialsProvider(new UsernamePasswordCredentialsProvider(username, password));
    }

    @Override
    public int order() {
        return 3;
    }

    @Override
    public boolean support(Git git) {
        return git instanceof GitUsernamePasswordClone;
    }
}
