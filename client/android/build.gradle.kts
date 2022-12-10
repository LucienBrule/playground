


plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")

}

group "io.brule.playground"
version "1.0-SNAPSHOT"


val jscFlavor = "org.webkit:android-jsc:+"


dependencies {
    implementation(project(":client:common"))


    // don't let this be auto updated it should stay at 0.70.6
    implementation("com.facebook.react:react-native:0.70.6")
    implementation("com.facebook.react:react-native-gradle-plugin")
    implementation(jscFlavor)
    debugImplementation("com.facebook.flipper:flipper:0.125.0") {
        exclude(group = "com.facebook.fbjni")
    }

    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.125.0") {
        exclude(group = "com.facebook.flipper")
        exclude(group = "com.squareup.okhttp3", module = "okhttp")
    }

    debugImplementation("com.facebook.flipper:flipper-fresco-plugin:0.125.0") {
        exclude(group = "com.facebook.flipper")
    }

    implementation("com.facebook.react:hermes-engine") {
        exclude(group = "com.facebook.fbjni")
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

apply(from = "${project.extra["nodeModules"]}/react-native/react.gradle")
