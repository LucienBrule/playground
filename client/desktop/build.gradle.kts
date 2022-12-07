import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("multiplatform")
    id("org.openjfx.javafxplugin")
    id("org.jetbrains.compose")
}

group = "io.brule.playground"
version = "1.0-SNAPSHOT"


kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(project(":client:common"))
                implementation(compose.desktop.currentOs)
                implementation(compose.uiTooling)
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.materialIconsExtended)
                implementation(compose.animation)
                implementation(compose.animationGraphics)
                implementation(compose.ui)
                implementation(compose.runtime)
                implementation("io.coil-kt:coil-compose:2.2.2")

            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            modules("java.instrument","java.net.http","java.jfr","jdk.jsobject","jdk.unsupported","jdk.unsupported.desktop", "jdk.xml.dom")

            targetFormats(TargetFormat.Rpm)
            packageName = "io.brule.playground.desktop"
            packageVersion = "1.0.0"
        }
    }
}

javafx{
    version = "19"
    modules = listOf("javafx.controls", "javafx.swing","javafx.web", "javafx.graphics")
}
