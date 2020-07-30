#!/bin/bash

#------------------------------------------------
# 参考自 hutool 工具
# 升级hb0730-commons版本，包括：
# 1. 升级pom.xml中的版本号
# 2. 替换README.md中的版本号
#------------------------------------------------

if [ ! -n "$1" ]; then
        echo "ERROR: 新版本不存在，请指定参数1"
        exit
fi

# 替换所有模块pom.xml中的版本
mvn versions:set -DnewVersion=$1

version=${1%-SNAPSHOT}

# 替换其它地方的版本
source $(pwd)/bin/replaceVersion.sh "$1"
