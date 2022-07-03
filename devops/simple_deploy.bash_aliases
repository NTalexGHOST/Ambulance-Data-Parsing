#!/bin/bash

yum install git

echo -n "Repository name: "
read repo_name

git init --bare $repo_name\.git


git clone 

touch $repo_name\.git/hooks/post-receive

#!/bin/bash

git --work-tree=/home/user/Ambulance-Data-Parsing --git-dir=/home/user/Ambulance-Data-Parsing.git checkout -f

mkdir /home/user/parse-result

