rootProject.name = "playground"
include("client")
include("server")
include("deployment")
include("lib")
include("client:android")
include("client:desktop")
include("client:common")

pluginManagement {
    val kotlinVersion: String by settings
    val quarkusVersion: String by settings
    val androidVersion: String by settings
    val composeVersion: String by settings

    plugins {

        // General
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion

        // Backend
        id("io.quarkus") version quarkusVersion

        // Android
        kotlin("android") version kotlinVersion
        id("com.android.application") version "7.3.0"
        id("com.android.library") version "7.3.0"
        id("org.jetbrains.compose") version composeVersion
    }

    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}
