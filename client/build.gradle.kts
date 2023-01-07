import playground.utils.bash

group "io.brule.playground"
version "1.0-SNAPSHOT"

plugins {
    base
    kotlin("multiplatform") apply false
    id("org.jetbrains.compose") apply false
}


subprojects {




    repositories {
        // for android dependencies
        google()

        // for compose
        gradlePluginPortal()

        // you can use jitpack to get the latest version of a library from github
        // the format is com.github.<github username>:<repo name>:<commit hash>
        // you can also use tags or branches instead of commit hashes
        maven {
            url = uri("https://jitpack.io")
        }

        // needed for react-native-gradle-plugin
        flatDir {
            dirs(project(":client").projectDir.absolutePath + "/libs")
        }

        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}


