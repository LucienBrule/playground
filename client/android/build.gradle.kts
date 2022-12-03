import org.gradle.internal.impldep.org.junit.experimental.categories.Categories.CategoryFilter.exclude

val enableSeperateBuildPerCPUArchitecture = false
val enableProgaurdInReleaseBuilds = false
val jscFlavor = "org.webkit:android-jsc:+"
val enableHermes = false


plugins {
    id("org.jetbrains.compose")
    id("com.android.application")
    kotlin("android")

}

group "io.brule.playground"
version "1.0-SNAPSHOT"

val nodeModules: String = project(":client").projectDir.absolutePath + "/node_modules"

repositories {
    google()
    gradlePluginPortal()
    // exclude facebook from maven central because it we load it from node_modules
    // see https://github.com/facebook/react-native/issues/35210
    mavenCentral{
        content {
            excludeGroup("com.facebook.react")
        }
    }

    maven {
        url = uri("$nodeModules/react-native/android")
    }
    maven {
        url = uri("$nodeModules/jsc-android/dist")
    }


}

dependencies {
    implementation(project(":client:common"))
//    implementation("androidx.activity:activity-compose:1.6.1")
//    implementation("androidx.compose.material:material:1.3.1")
//    implementation("com.facebook.react:react-native:+")

    implementation("com.facebook.react:react-native:0.70.6")
    implementation("com.facebook.react:react-native-gradle-plugin")

    debugImplementation("com.facebook.flipper:flipper:0.125.0"){
        exclude(group="com.facebook.fbjni")
    }

    debugImplementation("com.facebook.flipper:flipper-network-plugin:0.125.0"){
        exclude(group="com.facebook.flipper")
        exclude(group="com.squareup.okhttp3", module="okhttp")
    }

    debugImplementation("com.facebook.flipper:flipper-fresco-plugin:0.125.0"){
        exclude(group="com.facebook.flipper")
    }

    implementation("com.facebook.react:hermes-engine"){
        exclude(group="com.facebook.fbjni")
    }

    implementation(jscFlavor)



}

 buildscript {

    dependencies {
//        classpath("com.android.tools.build:gradle:7.2.1")
//        classpath("de.undercouch:gradle-download-task:5.0.2")
    }
}


val androidCompileSDKVersion: String by project
val androidTargetSDKVersion: String by project
val androidMinSDKVersion: String by project
val appVersion: String by project
val appVersionName: String by project
val androidBuildToolsVersion: String by project

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
project.extra["react"] = mapOf(
    "cliPath" to project(":client").projectDir.absolutePath + "/node_modules/react-native/cli.js",
    "root" to project(":client").projectDir.absolutePath, // the react root
    "bundleConfig" to "metro.config.js",
    "entryFile" to "ui/src/index.js",
    "bundleAssetName" to "index.android.bundle",
    "enableHermes" to true,
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


apply(from = "$nodeModules/react-native/react.gradle")

