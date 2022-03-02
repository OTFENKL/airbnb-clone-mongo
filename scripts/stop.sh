#!/bin/bash

echo "> kill -15 ${CURRENT_PID}"
kill -15 ${CURRENT_PID}
echo "> ${CURRENT_PID} 삭제 완료"
