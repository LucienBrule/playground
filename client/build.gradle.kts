import playground.utils.bash

group "io.brule.playground"
version "1.0-SNAPSHOT"

plugins {
    base
    kotlin("multiplatform") apply false
    id("org.jetbrains.compose") apply false
}

val enableSeperateBuildPerCPUArchitecture = false
val enableProgaurdInReleaseBuilds = false
val enableHermes = true
val nodeModules: String =
    project(":client").projectDir.absolutePath + "/node_modules"

subprojects {


    this.extra.apply {
        set("nodeModules", nodeModules)
    }

    repositories {
        // for android dependencies
        google()

        // for compose
        gradlePluginPortal()

        // exclude facebook from maven central
        // because it we load it from node_modules
        mavenCentral {
            content {
                excludeGroup("com.facebook.react")
            }
        }

        // load com.facebook.react from node_modules
        maven {
            url = uri("$nodeModules/react-native/android")
        }
        // load jsc from node_modules
        maven {
            url = uri("$nodeModules/jsc-android/dist")
        }

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

configurations.all {
    resolutionStrategy {
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


project.extra["react"] = mapOf(
    "cliPath" to project(":client").projectDir.absolutePath + "/node_modules/react-native/cli.js",
    "root" to project(":client").projectDir.absolutePath, // the react root
    "bundleConfig" to "metro.config.js",
    "entryFile" to "ui/src/index.js",
    "bundleAssetName" to "index.android.bundle",
    "enableHermes" to enableHermes,
    "hermesCommand" to "$nodeModules/react-native/sdks/hermesc/linux64-bin/hermesc",
    "composeSourceMapsPath" to "$nodeModules/react-native/scripts/compose-source-maps.js",
    "bundleInDebug" to true,
    "bundleInRelease" to true,
    "jsBundleDirDebug" to "$buildDir/intermediates/assets/debug",
    "jsBundleDirRelease" to "$buildDir/intermediates/assets/release",
    "resourcesDirDebug" to "$buildDir/intermediates/res/merged/debug",
    "resourcesDirRelease" to "$buildDir/intermediates/res/merged/release",
    "inputExcludes" to listOf("node_modules/**"),
    "nodeExecutableAndArgs" to listOf("node"),
)


