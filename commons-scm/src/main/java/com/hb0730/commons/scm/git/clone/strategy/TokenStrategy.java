package com.hb0730.commons.scm.git.clone.strategy;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.scm.git.builder.Git;
import com.hb0730.commons.scm.git.clone.GitCloneCommand;
import com.hb0730.commons.scm.git.clone.GitCloneStrategy;
import com.hb0730.commons.scm.git.clone.builder.GitTokenClone;
import org.eclipse.jgit.api.CloneCommand;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

/**
 * 通过token方式进行克隆
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class TokenStrategy extends GitCloneStrategy implements GitCloneCommand {

    @Override
    protected CloneCommand getCommand() {
        final String token = ((GitTokenClone) getGit()).getToken();
        if (StringUtils.isNotBlank(token)) {
            return getSuperCommand().setCredentialsProvider(new UsernamePasswordCredentialsProvider("PRIVATE-TOKEN", token));
        }
        return null;
    }

    @Override
    public int order() {
        return 2;
    }

    @Override
    public boolean support(Git git) {
        return git instanceof GitTokenClone;
    }
}
