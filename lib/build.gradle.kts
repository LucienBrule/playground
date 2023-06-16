description = "lib"
group = "io.brule"
version = "alpha"


val ktor_version: String by project
publishing{
    repositories{
        mavenLocal()
    }
}

plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization")
    id("maven-publish")
}
repositories {
    mavenCentral()
    mavenLocal()
}


kotlin {
    jvm()
    js(IR) {
        binaries.executable()
        compilations["main"].packageJson {
            version = "0.0.0"
        }
        browser {

        }

    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor_version")
            }
        }
        val jvmMain by getting
        val jsMain by getting


    }
}

