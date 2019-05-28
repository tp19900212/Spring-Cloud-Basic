FROM centos
LABEL maintainer="quyc07@163.com"
ENV REFRESHED_AT 2019-05-28

RUN yum install -y java
RUN yum install -y net-tools.x86_64
RUN yum install -y wget
RUN yum install -y curl

# 添加宿主机当前目录下的jar到容器对应目录
ADD learn-1.0-SNAPSHOT.jar /opt/dev/learn-1.0-SNAPSHOT.jar
#RUN cd /opt/dev
EXPOSE 8080
# 执行启动命令
ENTRYPOINT java -jar /opt/dev/learn-1.0-SNAPSHOT.jar
