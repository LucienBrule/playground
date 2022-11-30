plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")
}

group "io.brule.playground"
version "1.0-SNAPSHOT"

repositories {
    google()
    gradlePluginPortal()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

dependencies {
    implementation(project(":client:common"))
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.compose.material:material:1.3.1")
}

val androidCompileSDKVersion: String by project
val androidTargetSDKVersion: String by project
val androidMinSDKVersion: String by project
val appVersion: String by project
val appVersionName: String by project

android {
    compileSdk = androidCompileSDKVersion.toInt()
    defaultConfig {
        applicationId = "io.brule.playground.android"
        minSdk = androidMinSDKVersion.toInt()
        targetSdk = androidTargetSDKVersion.toInt()
        versionCode = appVersion.toInt()
        versionName = appVersionName
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    buildToolsVersion = "33.0.1"
    namespace = "io.brule.playground.android"
}
