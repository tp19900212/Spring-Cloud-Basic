FROM centos
LABEL maintainer="quyc07@163.com"
ENV REFRESHED_AT 2019-05-30
# 安装相关软件
#RUN yum install -y net-tools.x86_64
RUN yum install -y wget
#RUN yum install -y curl
#RUN yum install -y unzip
# 安装java11
WORKDIR /opt/dev
RUN wget https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz
RUN rm openjdk-11+28_linux-x64_bin.tar.gz
RUN tar -xvf openjdk-11+28_linux-x64_bin.tar.gz
ENV JAVA_HOME=/opt/dev/jdk-11
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
RUN source /etc/profile
