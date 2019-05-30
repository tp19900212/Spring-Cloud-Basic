FROM centos
MAINTAINER quyc "quyc07@163.com"
ENV REFRESHED_AT 2019-05-27

RUN yum install -y java
RUN yum install -y net-tools.x86_64
RUN yum install -y wget
RUN yum install -y tomcat

VOLUME [ "/opt/dev/tomcat9/webapps" ]

EXPOSE 8080

#ENTRYPOINT [ "/usr/share/tomcat/bin/catalina.sh", "run"]
