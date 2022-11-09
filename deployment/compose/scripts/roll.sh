#!/usr/bin/env bash

set -e

function roll(){
  ./scripts/down.sh
  ./scripts/clean.sh
  ./scripts/build.sh
  ./scripts/image.sh
  ./scripts/up.sh
}

function main(){
  # check first parameter and nuke if it's equal to nuke
  if [ "$1" == "nuke" ]; then
    ./scripts/nuke.sh
  fi

  time roll
}

main "$@"