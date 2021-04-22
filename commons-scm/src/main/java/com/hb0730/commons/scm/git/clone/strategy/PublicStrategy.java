package com.hb0730.commons.scm.git.clone.strategy;

import com.hb0730.commons.scm.git.builder.Git;
import com.hb0730.commons.scm.git.clone.GitCloneStrategy;
import org.eclipse.jgit.api.CloneCommand;

/**
 * public Repository,公开仓库只需要路径即可
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class PublicStrategy extends GitCloneStrategy {
    @Override
    public int order() {
        return 999;
    }

    @Override
    public boolean support(Git git) {
        return true;
    }

    @Override
    protected CloneCommand getCommand() {
        return getSuperCommand();
    }
}
