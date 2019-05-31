#!/bin/bash
# 项目部署目标路径
deploy_path=/opt/dev/jenkins_deploy/learn_docker/
# 传递应用程序和Dockerfile（宿主机地址为相对JENKINS_HOME地址）
mv learn/target/*.jar "$deploy_path"/learn.jar
mv docker/learn/Dockerfile /opt/dev/jenkins_deploy/learn_docker
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
docker build -t="quyc07/learn" "$deploy_path"/.
# 启动容器
docker run -d --name learn_docker -p8092:8080 quyc07/learn
