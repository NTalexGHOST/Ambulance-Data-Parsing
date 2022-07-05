#!/bin/bash

git --work-tree=/home/user/Ambulance-Data-Parsing --git-dir=/home/user/Ambulance-Data-Parsing/.git checkout -f

# mkdir /home/user/parse-result

echo "|-----------------------------------------------|"
echo "| Update received, start compile and reboot app |"
echo "|-----------------------------------------------|"

docker start compile-jar
if [ $? -eq 0 ]; then
	echo "Start compiling..."
else
	echo "Start running and compiling..."
	docker run -it -v /home/user/ambulance_data_app/server:/Ambulance-Data-Parsing/target -v /home/user/Ambulance-Data-Parsing/:/Ambulance-Data-Parsing --name compile-jar compile-jar
fi
docker compose -f /home/user/Ambulance-Data-Parsing/devops/docker-compose.yml restart
if [ $? -eq 0 ]; then
	echo "Services restart"
else
	docker compose -f /home/user/Ambulance-Data-Parsing/devops/docker-compose.yml up
fi
docker ps
