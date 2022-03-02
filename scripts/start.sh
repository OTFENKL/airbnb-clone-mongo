#!/bin/bash

echo "> ${APP_NAME} 배포"
nohup java -jar ${APP_DIR}/${APP_NAME} > /dev/null 2> /dev/null < /dev/null &
