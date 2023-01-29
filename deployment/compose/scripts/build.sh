#!/usr/bin/env bash

# Fail on error
set -e

# Build sources for container image generation

( cd ../../ && ./gradlew :server:clean :server:build)