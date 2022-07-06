#!/bin/bash

echo -n "Select branch to checkout: "
read branch
git checkout $branch -f
mv ./Ambulance-Data-Parsing/devops/post-receive.sh ./Ambulance-Data-Parsing/.git/hooks/post-receive
chmod +x ./Ambulance-Data-Parsing/.git/hooks/post-receive
