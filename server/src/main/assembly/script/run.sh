#!/bin/bash

PROFILE=''
MAIN_CLASS=com.team.assessment.TeamAssessmentApplication
#脚本所在目录
cd `dirname $0`
#BIN目路
BIN_DIR=`pwd`
cd ..
#部署目路
DEPLOY_DIR=`pwd`
#获取到当前目录的名称
SERVER_NAME=`basename $DEPLOY_DIR`
#配置文件目录
CONF_DIR=$DEPLOY_DIR/conf
#项目依赖库
LIB_DIR=$DEPLOY_DIR/lib
#遍历整个目录的jar包
LIB_JARS=`ls $LIB_DIR|grep .jar|awk '{print "'$LIB_DIR'/"$0}'|tr "\n" ":"`
#执行的jar包
MAIN_CLASS_JAR=`ls $DEPLOY_DIR|grep .jar|awk '{print "'$DEPLOY_DIR'/"$0}'|tr "\n" ":"`
#应用进程
PIDS=`ps -ef | grep java | grep "$CONF_DIR" |awk '{print $2}'`
#设置日志文件的输出目录
LOGS_DIR=$DEPLOY_DIR/logs
REGION=$3
ENV=$2
if [ ! -d $LOGS_DIR ]; then
    mkdir $LOGS_DIR
fi

#日志
STDOUT_FILE=$LOGS_DIR/stdout.log
#JAVA 环境配置
JAVA_OPTS=" -Djava.net.preferIPv4Stack=true -Dlog.home=$LOGS_DIR"
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -server -Xms512m -Xmx2g -XX:PermSize=128m -XX:MaxPermSize=128m -XX:SurvivorRatio=6 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=80 -XX:+ScavengeBeforeFullGC -XX:+CMSScavengeBeforeRemark -XX:+PrintGCDateStamps -verbose:gc -XX:+PrintGCDetails -Xloggc:gc.log -XX:+UseGCLogFileRotation -XX:NumberOfGCLogFiles=10 -XX:GCLogFileSize=100M -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=dump.hprof "
else
    JAVA_MEM_OPTS=" -server -Xms512m -Xmx1g -XX:PermSize=128m -XX:MaxPermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi

#退出标志
RETVAL="0"

function start() {
	if [ -n "$PIDS" ]; then                                                         
		echo "ERROR: The $SERVER_NAME already started!"
		echo "PID: $PIDS"
		exit $RETVAL
	fi
	
	echo -e "Starting the $SERVER_NAME ...\c"
	nohup java $JAVA_OPTS $JAVA_MEM_OPTS -classpath $CONF_DIR:$LIB_JARS:$MAIN_CLASS_JAR $MAIN_CLASS $REGION $ENV $PROFILE > /dev/null 2>&1 &
	
	COUNT=0
	while [ $COUNT -lt 1 ]; do                 
		echo -e ".\c"                         
		sleep 1                            
		COUNT=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}' | wc -l`
		if [ $COUNT -gt 0 ]; then
			break
		fi
	done
  
	echo "OK!"                                      
	PIDS=`ps -f | grep java | grep "$DEPLOY_DIR" | awk '{print $2}'`
	echo "PID: $PIDS"
	echo "STDOUT: $STDOUT_FILE"
	
}

function stop() {
	if [ -z "$PIDS" ]; then
    		echo "ERROR: The $SERVER_NAME does not started!"
    	exit 1
	fi

	echo -e "Stopping the $SERVER_NAME ...\c"
	for PID in $PIDS ; do
    		kill $PID > /dev/null 2>&1
	done

	COUNT=0
	while [ $COUNT -lt 1 ]; do
    		echo -e ".\c"
    		sleep 1
    		COUNT=1
    		for PID in $PIDS ; do
        	PID_EXIST=`ps -f -p $PID | grep java`
        	if [ -n "$PID_EXIST" ]; then
            		COUNT=0
            		break
        	fi
    		done
	done

	echo "OK!"
	echo "PID: $PIDS"
	PIDS=""
}


function usage() {
	echo "Usage: $0 {start|stop|restart|status}"
	RETVAL="2"
}

#退出标志
RETVAL="0"

if [ ! -n "$2" ] ;then
	PROFILE=''
else
	PROFILE='--spring.profiles.active='$2
fi

case $1 in
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
                ;;
esac
exit $RETVAL
