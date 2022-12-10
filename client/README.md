# Playground Client

## Android

How to run it in dev:

1. `yarn install` in client
2. ./gradlew check in root
3. ./gradlew :client:android:build
4. ./gradlew :client:android:installDebug
5. `yarn start` in client
6. `adb shell am start -n com.playground/.MainActivity`
7. `adb logcat | grep ReactNativeJS` to see logs


