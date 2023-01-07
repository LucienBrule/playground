


plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("multiplatform")
}

group "io.brule.playground"
version "1.0-SNAPSHOT"



kotlin{
    android()
    sourceSets{
        val androidMain by getting{
            dependencies{
                implementation(project(":client:common"))
                implementation("androidx.appcompat:appcompat-resources:1.5.1")
                implementation("androidx.activity:activity-compose:1.6.1")
            }
        }
    }
}


val androidCompileSDKVersion: String by project
val androidTargetSDKVersion: String by project
val androidMinSDKVersion: String by project
val androidBuildToolsVersion: String by project
val appVersion: String by project
val appVersionName: String by project

extra.apply {
    set("buildToolsVersion", androidBuildToolsVersion)
    set("minSdkVersion", androidMinSDKVersion)
    set("compileSdkVersion", androidCompileSDKVersion)
    set("targetSdkVersion", androidTargetSDKVersion)
}

android {
    compileSdk = androidCompileSDKVersion.toInt()
    buildToolsVersion = androidBuildToolsVersion
    namespace = "io.brule.playground.android"


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
}
