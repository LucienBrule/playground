#!/usr/bin/env bash
echo "
     _ _            _
  ___| (_) ___ _ __ | |_
 / __| | |/ _ \ '_ \| __|
| (__| | |  __/ | | | |_
 \___|_|_|\___|_| |_|\__|
 "


source ~/.sdkman/bin/sdkman-init.sh

./gradlew :client:browserRun --continuous