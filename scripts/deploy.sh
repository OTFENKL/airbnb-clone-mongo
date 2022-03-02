#!/bin/bash

APP_DIR=/home/ec2-user/app/airbnb-mongo/archive-jar

APP_NAME=$(ls $APP_DIR | grep .jar)
CURRENT_PID=$(pgrep -f $APP_NAME)

if [ -z $CURRENT_PID ]
then
  echo "> 종료할 APP이 없습니다."
else
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID
  echo "> $CURRENT_PID 삭제 완료"
fi

echo "> $APP_NAME 배포"
nohup java -jar $APP_DIR/$APP_NAME > /dev/null 2> /dev/null < /dev/null &