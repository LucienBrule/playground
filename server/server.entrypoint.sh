#!/usr/bin/env bash

# display banner
echo "
 ___  ___ _ ____   _____ _ __
/ __|/ _ \ '__\ \ / / _ \ '__|
\__ \  __/ |   \ V /  __/ |
|___/\___|_|    \_/ \___|_|"

#source ~/.sdkman/bin/sdkman-init.sh
## Install lib to local maven cache
##./gradlew :lib:publishToMavenLocal
##
##echo "published lib to local maven cache"
#
#
## Run the server
#./gradlew :server:build
#
#./gradlew :server:quarkusDev

exec java -jar /deployments/quarkus-run.jar

exit 0