FROM quyc07/base
LABEL maintainer="quyc07@163.com"
ENV REFRESHED_AT 2019-05-30
# 添加jar包
COPY learn.jar /opt/dev/learn.jar
# 开放8080端口
EXPOSE 8080
# 启动工程
ENTRYPOINT java -jar /opt/dev/learn.jar
