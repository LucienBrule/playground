#!/usr/bin/env bash

set -e


# Nuke all docker containers and images
docker ps -aq | while read i; do docker rm $i; done
docker image ls -aq | while read i; do docker rmi --force $i; done
