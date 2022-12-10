#!/usr/bin/env bash
echo "Starting web client..."


./gradlew :web:browserRun --no-daemon --continuous
