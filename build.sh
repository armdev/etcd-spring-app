#!/usr/bin/env bash

set -e

echo "Build The Future"

# mvn clean package -U -Dmaven.test.skip=true
if [[ $1 == "clean" ]]; then
  echo "Performing a clean build"
  mvn clean package -T 1C -Dmaven.test.skip=true
else
  echo "Performing an incremental build"
  mvn package -T 1C -Dmaven.test.skip=true
fi