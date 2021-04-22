package com.hb0730.commons.scm.git.clone;

import com.hb0730.commons.lang.collection.CollectionUtils;
import com.hb0730.commons.scm.git.builder.Git;
import org.eclipse.jgit.api.CloneCommand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * 查找所有的{@link GitCloneCommand}
 *
 * @author bing_huang
 * @since 2.1.2
 */
public class GitStrategy {
    private final CloneCommand command;
    private final Git git;
    private List<GitCloneCommand> dos;

    /**
     * 有参构造
     *
     * @param command {@link CloneCommand}
     * @param git     {@link Git}
     */
    public GitStrategy(CloneCommand command, Git git) {
        this.command = command;
        this.git = git;
        this.dos = load();

    }

    /**
     * 执行
     *
     * @return {@link CloneCommand}
     */
    public CloneCommand doCommand() {
        return foreach(dos);
    }

    /**
     * 重新加载所有实现类{@link GitCloneCommand}
     *
     * @return 已排序的 {@link GitCloneCommand}
     */
    public List<GitCloneCommand> load() {
        List<GitCloneCommand> dos = new ArrayList<>();
        ServiceLoader<GitCloneCommand> load = ServiceLoader.load(GitCloneCommand.class);
        for (GitCloneCommand gitClone : load) {
            GitCloneStrategy cloneStrategy = (GitCloneStrategy) gitClone;
            cloneStrategy.setGit(git);
            cloneStrategy.setCommand(command);
            dos.add(gitClone);
        }
        dos.sort(Comparator.comparing(GitCloneCommand::order));
        this.dos = dos;
        return dos;
    }

    private CloneCommand foreach(List<GitCloneCommand> dos) {
        if (CollectionUtils.isEmpty(dos)) {
            return null;
        }
        List<GitCloneCommand> supportGitClone = dos.stream()
                .filter(gitClone -> gitClone.support(git))
                .collect(Collectors.toList());
        for (GitCloneCommand gitClone : supportGitClone) {
            CloneCommand command = gitClone.doCommand();
            if (null != command) {
                return command;
            }
        }
        return null;
    }
}
