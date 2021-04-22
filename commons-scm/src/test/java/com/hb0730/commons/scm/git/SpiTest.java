package com.hb0730.commons.scm.git;

import com.hb0730.commons.scm.git.clone.GitCloneCommand;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 */
public class SpiTest {

    @Test
    public void test() {
        ServiceLoader<GitCloneCommand> load = ServiceLoader.load(GitCloneCommand.class, this.getClass().getClassLoader());
        Iterator<GitCloneCommand> iterator = load.iterator();
        while (iterator.hasNext()){
            GitCloneCommand next = iterator.next();
        }

    }
}
