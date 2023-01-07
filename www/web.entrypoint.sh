#!/usr/bin/env bash
echo "Starting web client..."


./gradlew :www:browserRun --no-daemon --continuous
