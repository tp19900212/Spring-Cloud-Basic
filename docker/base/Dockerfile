FROM centos
MAINTAINER quyc07@163.com
ENV REFRESHED_AT 2019-05-30
# 安装相关软件
RUN yum install -y wget && \
#yum install -y net-tools.x86_64 && \
#yum install -y unzip && \
    yum clean all && \
# 安装java11
    wget https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz && \
    tar -xvf openjdk-11+28_linux-x64_bin.tar.gz && \
    rm openjdk-11+28_linux-x64_bin.tar.gz
ENV JAVA_HOME=/opt/dev/jdk-11 \
    PATH=$JAVA_HOME/bin:$PATH \
    CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar \
RUN source /etc/profile
