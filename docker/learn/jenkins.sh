#!/bin/bash
# 切换到部署目录
cd /opt/dev/jenkins_deploy/learn_docker
# 传递应用程序和Dockerfile
mv /var/lib/jenkins/workspace/learn/learn/target/*.jar /opt/dev/jenkins_deploy/learn_docker/learn.jar
mv /var/lib/jenkins/workspace/learn/docker/learn/Dockerfile /opt/dev/jenkins_deploy/learn_docker
# 获取运行中的容器ID
container_id=$(docker ps --filter name=learn_docker -q)
# 获取镜像ID
image_id=$(docker images --filter reference=quyc07/learn:* -q)
# 运行中的容器存在
if [ -n "$container_id" ]
then
    # 关闭容器，删除容器
    docker stop "$container_id"
    docker rm "$container_id"
else
    # 获取已关闭的容器
    container_id= $(docker ps -a --filter name='learn_docker' -q)
    # 已关闭容器存在
    if [ -n "$container_id" ]
    then
        # 删除容器
        docker rm "$container_id"
    fi
fi
# 镜像是否存在
if [ -n "$image_id" ]
then
    # 存在则删除
    docker rmi "$image_id"
fi
# 构建镜像
docker build -t="quyc07/learn" .
# 启动容器
docker run -d --name learn_docker -p8092:8080 quyc07/learn
