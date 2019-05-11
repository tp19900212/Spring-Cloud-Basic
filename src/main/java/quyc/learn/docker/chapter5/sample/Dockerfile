FROM centos
MAINTAINER quyc "quyc07@163.com"
ENV REFRESHED_AT 2019-05-11
RUN rpm -ivh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm
RUN yum install -y nginx
RUN mkdir -p /var/www/html/website
RUN rm -f /etc/nginx/conf.d/default.conf
ADD nginx/global.conf /etc/nginx/conf.d/
ADD nginx/nginx.conf /etc/nginx/nginx.conf
EXPOSE 80