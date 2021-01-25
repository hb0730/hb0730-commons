#!/bin/bash

echo -e "\033[32mCheckout to dev\033[0m"
git checkout dev

echo -e "\033[32mMerge master branch\033[0m"
git merge master -m "Prepare release"
