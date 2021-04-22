package com.hb0730.commons.scm.git;

/**
 *
 */
public class GitTest {
    public static PrivateHttps privateHttps() {
        return new PrivateHttps();
    }

    public static PrivateSsh privateSsh() {
        return new PrivateSsh();
    }

    public static PrivateHttp privateHttp() {
        return new PrivateHttp();
    }

    public static PrivateHttpToken privateHttpToken() {
        return new PrivateHttpToken();
    }

    public static PrivateSshToken privateSshToken() {
        return new PrivateSshToken();
    }


    public static class PrivateHttps implements GitConfig {

        @Override
        public String remoteUrl() {
            return "https://github.com/hb0730/short-url.git";
        }

        @Override
        public String username() {
            return PRIVATE_HTTPS_USERNAME;
        }

        @Override
        public String password() {
            return PRIVATE_HTTPS_PASSWORD;
        }

        @Override
        public String branch() {
            return "main";
        }
    }

    public static class PrivateSsh implements GitConfig {

        @Override
        public String remoteUrl() {
            return "git@github.com:hb0730/short-url.git";
        }

        @Override
        public String username() {
            return PRIVATE_HTTPS_USERNAME;
        }

        @Override
        public String password() {
            return PRIVATE_HTTPS_PASSWORD;
        }

        @Override
        public String branch() {
            return "main";
        }
    }

    public static class PrivateHttp implements GitConfig {

        @Override
        public String remoteUrl() {
            return "http://xx/xx.git";
        }

        @Override
        public String username() {
            return PRIVATE_HTTP_USERNAME;
        }

        @Override
        public String password() {
            return PRIVATE_HTTP_PASSWORD;
        }

        @Override
        public String branch() {
            return "master";
        }
    }

    public static class PrivateHttpToken implements GitConfig {

        @Override
        public String remoteUrl() {
            return "http://xxx/xxx.git";
        }

        @Override
        @Deprecated
        public String username() {
            return null;
        }

        @Override
        @Deprecated
        public String password() {
            return null;
        }

        @Override
        public String branch() {
            return "master";
        }

        public String token() {
            return PRIVATE_HTTP_TOKEN;
        }
    }

    public static class PrivateSshToken implements GitConfig {

        @Override
        public String remoteUrl() {
            return "git@github.com:hb0730/short-url.git";
        }

        @Override
        @Deprecated
        public String username() {
            return null;
        }

        @Override
        @Deprecated
        public String password() {
            return null;
        }

        @Override
        public String branch() {
            return "main";
        }

        public String token() {
            return PRIVATE_HTTPS_TOKEN;
        }
    }

    public interface GitConfig {
        String PRIVATE_HTTPS_USERNAME = "";
        String PRIVATE_HTTPS_PASSWORD = "";
        String PRIVATE_HTTP_USERNAME = "";
        String PRIVATE_HTTP_PASSWORD = "";
        String PRIVATE_HTTPS_TOKEN = "";
        String PRIVATE_HTTP_TOKEN = "";

        String remoteUrl();

        String username();

        String password();

        String branch();
    }
}
