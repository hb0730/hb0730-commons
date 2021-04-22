package com.hb0730.commons.scm.git.clone.builder;

import com.hb0730.commons.lang.StringUtils;
import com.hb0730.commons.lang.builder.Builder;
import com.hb0730.commons.scm.git.builder.Git;
import com.hb0730.commons.scm.git.clone.GitStrategy;
import org.eclipse.jgit.api.CloneCommand;

/**
 * Git Clone 参数
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitCloneBuilder implements Builder<CloneCommand> {
    private static final String BRANCH_MASTER = "master";
    private final Git git;

    public GitCloneBuilder(Git git) {
        this.git = git;
    }

    public static <T extends Git> GitCloneBuilder builder(Builder<T> build) {
        return builder(build.build());
    }

    public static GitCloneBuilder builder(Git git) {
        return new GitCloneBuilder(git);
    }

    @Override
    public CloneCommand build() {
        CloneCommand command = org.eclipse.jgit.api.Git.cloneRepository()
                .setURI(git.getRemoteUrl())
                .setDirectory(git.getLocalDirectory())
                .setBranch(StringUtils.isBlank(git.getBranch()) ? BRANCH_MASTER : git.getBranch());
        return new GitStrategy(command, git).doCommand();
    }


}
