#!/usr/bin/env bash

# Fail on error
set -e
echo $(pwd)
( cd ../../ && ./gradlew clean )
