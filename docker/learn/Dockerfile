FROM quyc07/base
LABEL maintainer="quyc07@163.com"
ENV REFRESHED_AT 2019-05-30
# 添加jar包
COPY learn.jar /opt/dev/learn.jar
# 挂载卷
VOLUME ["/opt/dev/volume"]
# 开放8090端口
EXPOSE 8090
# 启动工程
ENTRYPOINT java -jar /opt/dev/learn.jar
# 启用容器健康检查功能
HEALTHCHECK --interval=5s --timeout=3s \
CMD curl -f http://localhost:8090/checkHealth || exit 1