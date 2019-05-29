FROM centos
LABEL maintainer="quyc07@163.com"
ENV REFRESHED_AT 2019-05-28
# 安装相关软件
#RUN yum install -y net-tools.x86_64
RUN yum install -y wget
#RUN yum install -y curl
#RUN yum install -y unzip
# 安装java11
WORKDIR /opt/dev
RUN wget https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz
RUN tar -xvf jdk-11.0.3_linux-x64_bin.tar.gz
RUN mv jdk-11.0.3_linux-x64_bin.tar.gz/jdk-11 jdk-11
ENV JAVA_HOME=/opt/dev/jdk-11
ENV PATH=$JAVA_HOME/bin:$PATH
ENV CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
RUN source /etc/profile
# 添加jar包
COPY learn-1.0-SNAPSHOT.jar /opt/dev/learn-1.0-SNAPSHOT.jar
# 开放8080端口
EXPOSE 8080
# 启动工程
ENTRYPOINT java -jar /opt/dev/learn-1.0-SNAPSHOT.jar
