#!/bin/bash

APP_DIR=/home/ec2-user/app/airbnb-mongo/archive-jar

APP_NAME=$(ls $APP_DIR | grep .jar)
CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할 APP이 없습니다."
else
  $APP_DIR/stop.sh
fi

$APP_DIR/start.sh