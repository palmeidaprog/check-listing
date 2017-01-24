#!/bin/bash
# Script by Paulo R. Almeida Filho 
#<palmeidaprogramming@gmail.com>

read -p "Merge everything with 'sync'? (Y/N) " -n 1 resp
echo    # (optional) move to a new line
if [[ $resp == "y" || $resp == "Y" ]];
then

	echo MERGING SYNC WITH DEVELOPMENT...
	git checkout dev
    git merge sync
	echo MERGING DEV WITH MASTER...
	git checkout master
	git merge dev
	echo ALL BRANCHES MERGED!
	git checkout sync
fi

