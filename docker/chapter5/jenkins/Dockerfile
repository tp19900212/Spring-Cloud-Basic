FROM centos
MAINTAINER quyc "quyc07@163.com"
ENV REFRESHED_AT 2019-05-12
RUN yum install -y java
RUN yum install -y net-tools.x86_64
RUN yum install -y wget
# 安装Jenkins
RUN wget -O /etc/yum.repos.d/jenkins.repo http://pkg.jenkins-ci.org/redhat/jenkins.repo
RUN rpm --import https://jenkins-ci.org/redhat/jenkins-ci.org.key
RUN yum install -y jenkins
#CMD ["java -jar /usr/lib/learn/learn.war"]
CMD ["/bin/bash"]
EXPOSE 8080