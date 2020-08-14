#上传 build/libs/config 目录
#上传 build/libs/lib  目录
#上传 fly-user-0.0.1-SNAPSHOT.jar
#多台服务器可以使用rsync进行同步
#启动脚本


#!/bin/bash

SERVER_NAME=fly-user-0.0.1-SNAPSHOT.jar
JAR=/data/java-server/fly-user-0.0.1-SNAPSHOT.jar
PID=`jps -l |grep $SERVER_NAME |awk '{print $1}'`

function start() {
	nohup java  -Xms256m -Xmx512m -Xmn256m -server -Dloader.path=lib -jar -Djava.security.egd=file:/dev/./urandom -Dsname=$SERVER_NAME $JAR --spring.profiles.active=prod > console.log &
  echo "Service is up!!"
  #sleep 1
  ##tail -50f console.log

}

function stop() {
	for id in $PID;do
		kill -9 $id
		echo "Service is down..."
	done
}
case "$1" in
    start)
      start
      ;;
    stop)
      stop
      ;;
    restart)
      stop
      start
      ;;
    *)
      testuser
      echo $"Usage: $0 {start|stop|deploy|restart}"
      exit 2
esac
exit $?