package com.hb0730.commons.scm.git;

import com.hb0730.commons.scm.git.clone.GitCloneCommand;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Iterator;
import java.util.ServiceLoader;

/**
 *
 */
@Slf4j
public class SpiTest {

    @Test
    public void test() {
        try {
            ServiceLoader<GitCloneCommand> load = ServiceLoader.load(GitCloneCommand.class, this.getClass().getClassLoader());
            Iterator<GitCloneCommand> iterator = load.iterator();
            while (iterator.hasNext()){
                GitCloneCommand next = iterator.next();
            }
        }catch (Throwable e){
            log.error(e.getMessage());
        }


    }
}
