#!/bin/bash

REPOSITORY=/home/ec2-user/app/airbnb-mongo
SCRIPT_DIR=$(dirs)

cd $REPOSITORY

JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_FILE=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $JAR_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  $SCRIPT_DIR/stop.sh
fi

echo "> $JAR_FILE 배포"
$SCRIPT_DIR/start.sh
