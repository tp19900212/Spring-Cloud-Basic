FROM centos
LABEL maintainer="quyc07@163.com"
ENV REFRESHED_AT 2019-05-27

RUN yum install -y curl unzip

# 下载网络资源
ADD https://releases.hashicorp.com/consul/0.3.1/consul_0.3.1_linux_amd64.zip /tmp/consul.zip
# 切换目录，解压文件，修改权限，删除临时文件
RUN cd /usr/sbin && unzip /tmp/consul.zip && chmod +x /usr/sbin/consul && rm /tmp/consul.zip
#
ADD consul.json /config/

EXPOSE 8300 8301 8301/udp 8302 8302/udp 8400 8500 53/udp

VOLUME ["/data"]

ENTRYPOINT [ "/usr/sbin/consul", "agent", "-config-dir=/config" ]
CMD []
