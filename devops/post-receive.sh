#!/bin/bash

git --work-tree=/home/user/Ambulance-Data-Parsing --git-dir=/home/user/Ambulance-Data-Parsing/.git checkout -f


echo "---- Update received, start compile and reboot app ----"


echo "---- Start compiling ----"
docker start -a stderr compile-jar
if [ $? -eq 0 ]; then
	echo -n ""
else
	echo "---- Create compile container ----"
	docker run -a stderr -v /home/user/Ambulance-Data-Parsing/target:/Ambulance-Data-Parsing/target -v /home/user/Ambulance-Data-Parsing/:/Ambulance-Data-Parsing --name devops-compile-jar compile-jar
fi
echo "---- Compiling complete ----"
echo "---- Application will be restart ----"
docker compose -f /home/user/Ambulance-Data-Parsing/devops/docker-compose.yml restart
echo "---- Application is running ----"
docker ps
