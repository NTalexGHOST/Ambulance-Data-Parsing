#!/bin/bash

git --work-tree=/home/use/Ambulance-Data-Parsing --git-dir=/home/user/Ambulance-Data-Parsing.git checkout -f

# mkdir /home/user/parse-result

echo "Update received, start compile and reboot app"

if [ docker start compile-jar ]; then
	echo "Start compiling..."
else
	echo "Start running and compiling..."
	docker run -it -v /home/user/ambulance_data_app/server:/Ambulance-Data-Parsing/target --name compile-jar compile-jar:v1
fi

docker compose reboot
