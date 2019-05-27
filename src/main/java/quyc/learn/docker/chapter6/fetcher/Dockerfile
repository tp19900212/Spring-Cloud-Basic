FROM centos
MAINTAINER quyc "quyc07@163.com"
ENV REFRESHED_AT 2019-05-27

RUN yum install -y java
RUN yum install -y net-tools.x86_64
RUN yum install -y wget

VOLUME [ "/opt/dev/tomcat9/webapps" ]
WORKDIR /opt/dev/tomcat9/webapps

ENTRYPOINT ["wget"]
CMD ["-?"]

# docker run -t -i --name fetcher_sample quyc/fetcher https://tomcat.apache.org/tomcat-7.0-doc/appdev/sample/sample.war