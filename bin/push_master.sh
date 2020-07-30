#!/bin/bash

echo -e "\033[32mCheckout to master\033[0m"
git checkout master

echo -e "\033[32mMerge dev branch\033[0m"
git merge dev -m "Prepare release"

echo -e "\033[32mPull master\033[0m"
git pull origin master

echo -e "\033[32mPush to origin master\033[0m"
git push origin master
