package com.hb0730.commons.lang.constants;

/**
 * 系统常量
 *
 * @author bing_huang
 * @since 1.0.1
 */
public interface SystemConst {

    /**
     * Java 运行时环境版本
     */
    String VERSION = "java.version";

    /**
     * Java 运行时环境供应商
     */
    String VENDOR = "java.vendor";

    /**
     * Java 供应商的 URL
     */
    String VENDOR_URL = "java.vendor.url";

    /**
     * Java 安装目录
     */
    String HOME = "java.home";

    /**
     * Java 虚拟机规范版本
     */
    String VM_SPECIFICATION_VERSION = "java.vm.specification.version";

    /**
     * Java 虚拟机规范供应商
     */
    String VM_SPECIFICATION_VENDOR = "java.vm.specification.vendor";

    /**
     * Java 虚拟机规范名称
     */
    String VM_SPECIFICATION_NAME = "java.vm.specification.name";

    /**
     * Java 虚拟机实现版本
     */
    String VM_VERSION = "java.vm.version";

    /**
     * Java 虚拟机实现供应商
     */
    String VM_VENDOR = "java.vm.vendor";

    /**
     * Java 虚拟机实现名称
     */
    String VM_NAME = "java.vm.name";

    /**
     * Java 运行时环境规范版本
     */
    String SPECIFICATION_VERSION = "java.specification.version";

    /**
     * Java 运行时环境规范供应商
     */
    String SPECIFICATION_VENDOR = "java.specification.vendor";

    /**
     * Java 运行时环境规范名称
     */
    public static final String SPECIFICATION_NAME = "java.specification.name";

    /**
     * Java 类格式版本号
     */
    String CLASS_VERSION = "java.class.version";

    /**
     * 类路径
     */
    String CLASS_PATH = "java.class.path";

    /**
     * 加载库时搜索的路径列表
     */
    String LIBRARY_PATH = "java.library.path";

    /**
     * 默认的临时文件路径
     */
    String IO_TMPDIR = "java.io.tmpdir";

    /**
     * 要使用的 JIT 编译器的名称
     */
    String COMPILER = "java.compiler";

    /**
     * 一个或多个扩展目录的路径
     */
    String EXT_DIRS = "java.ext.dirs";

    /**
     * 操作系统的名称
     */
    String OS_NAME = "os.name";

    /**
     * 操作系统的架构
     */
    String OS_ARCH = "os.arch";

    /**
     * 操作系统的版本
     */
    String OS_VERSION = "os.version";

    /**
     * 文件分隔符
     * UNIX /
     */
    String FILE_SEPARATOR = "file.separator";

    /**
     * 路径分隔符
     * Unix: <code>:</code> Win: <code>;</code>
     */
    String PATH_SEPARATOR = "path.separator";

    /**
     * 行分隔符
     * Unix: <code>/n</code> Win: <code>/r/n</code>
     */
    String LINE_SEPARATOR = "line.separator";

    /**
     * 用户的账户名称
     */
    String USER_NAME = "user.name";

    /**
     * 用户的主目录
     */
    String USER_HOME = "user.home";

    /**
     * 户的当前工作目录
     */
    String USER_DIR = "user.dir";

}
