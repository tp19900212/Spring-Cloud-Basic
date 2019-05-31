#!/bin/bash

set -m

if [ `whoami` != "zwjfadmin001" ];then
  echo "请使用zwjfadmin001用户执行!"
  exit 1
else

  PIDS=$(ps -ef | grep java | grep miuzone-base-service-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{print $2}')
  if [ "$PIDS" == "" ]; then
    nohup java -Xms2g -Xmx2g -jar miuzone-base-service-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod&
    sleep 1 
    tail -f nohup.out
  else
    echo "服务正在运行中，不要重复启动!"
  fi
fi
