import playground.utils.bash

group "io.brule.playground"
version "1.0-SNAPSHOT"

plugins {
    base
    kotlin("multiplatform") apply false
    id("org.jetbrains.compose") apply false
}

allprojects {

    repositories {
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")

        // exclude facebook from maven central
        // because it we load it from node_modules
        mavenCentral {
            content {
                excludeGroup("com.facebook.react")
            }
        }

        // you can use jitpack to get the latest version of a library from github
        // the format is com.github.<github username>:<repo name>:<commit hash>
        // you can also use tags or branches instead of commit hashes
        maven {
            url = uri("https://jitpack.io")
        }

        flatDir{
            dirs(project(":client").projectDir.absolutePath + "/libs")
        }
    }
}

configurations.all{
    resolutionStrategy{
        force("com.facebook.react:react-native:0.70.6")
    }
}

// 0
tasks.register("clientEntrypoint") {
    doLast {
        println("Hello from clientEntrypoint")
    }
}

// 1
tasks.register("fetchNpmDependencies") {
    doFirst {
        println("fetching npm dependencies")

        bash("yarn install", projectDir).let {
            println(it)
        }

    }
    doLast {
        println("done fetching npm dependencies")
    }
}


// 2
tasks.register("copyPluginsFromNodeModules") {
    doFirst {
        logger.info("copying plugins from node_modules")

        bash(
            "mkdir $buildDir; cp -r node_modules/react-native-gradle-plugin $buildDir/",
            projectDir
        ).let { println(it) }

    }
    doLast {
        println("done copying plugins from node_modules")
    }
}


// 3
tasks.register("buildReactNativeGradlePlugin") {
    doFirst {
        println("building react-native-gradle-plugin")

        bash(
            "cd $buildDir/react-native-gradle-plugin && ./gradlew build",
            projectDir
        ).let { println(it) }

    }
    doLast {
        println("done building react-native-gradle-plugin")
    }
}

// 4

tasks.register("copyReactNativeGradlePluginToLibs") {
    doFirst {
        println("copying react-native-gradle-plugin to libs")

        bash(
            "cp $buildDir/react-native-gradle-plugin/build/libs/react-native-gradle-plugin.jar $projectDir/libs/react-native-gradle-plugin.jar",
            projectDir
        )

    }
    doLast {
        println("done copying react-native-gradle-plugin to libs")
    }
}


// 3 depends on 2 depends on 1 depends on 0

tasks.named("copyReactNativeGradlePluginToLibs").configure {
    dependsOn("buildReactNativeGradlePlugin")
}

tasks.named("buildReactNativeGradlePlugin").configure {
    dependsOn("copyPluginsFromNodeModules")
}

tasks.named("copyPluginsFromNodeModules").configure {
    dependsOn("fetchNpmDependencies")
}

tasks.named("fetchNpmDependencies").configure {
    dependsOn("clientEntrypoint")
}

tasks["check"].dependsOn("copyReactNativeGradlePluginToLibs")
