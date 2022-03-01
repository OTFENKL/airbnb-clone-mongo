#!/bin/bash

REPOSITORY=/home/ec2-user/app/airbnb-mongo
cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_FILE=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  sh ./stop.sh
fi

echo "> $JAR_FILE 배포"
sh ./start.sh
