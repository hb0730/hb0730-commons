#!/bin/bash
echo $TRAVIS_TAG

if [ -z $TRAVIS_TAG ]; then
  echo "tag is null"
else
  echo "Publishing Maven snapshot..."
  mvn clean deploy --settings=util/settings.xml -DskipTests=true -B -U
fi
