#!/bin/bash

echo -n "Select branch to checkout: "
read branch
git checkout $branch -f
mv /home/user/Ambulance-Data-Parsing/devops/post-receive.sh /home/user/Ambulance-Data-Parsing/.git/hooks/post-receive
chmod +x /home/user/Ambulance-Data-Parsing/.git/hooks/post-receive
