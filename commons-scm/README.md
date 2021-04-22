# commons-scm
对SCM(git,svn)等支持
## git
使用[jgit]()对git操作的支持
### clone
1. username/password 方式

```java
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
```
2. ssh/key方式
```java
PrivateSsh ssh = privateSsh();
CloneCommand command = GitCloneBuilder.builder(
        GitSshKeyClone.GitSshKeyCloneBuilder.builder()
                .remoteUrl(ssh.remoteUrl())
                .branch(ssh.branch())
                .localDirectory(file)
                .keyPath("~/.ssh/id_rsa")
).build();
git = command.call();
```
等多种方式的支持
