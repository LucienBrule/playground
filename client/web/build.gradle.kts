plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

kotlin {
    js(IR) {
        browser()
        compilations["main"].packageJson {
            version = "0.0.0"
            dependencies {
                "webpack-bundle-analyzer" to "4.5.0"
            }
        }
        binaries.executable()
    }
    sourceSets {
        val jsMain by getting  {
            dependencies {
                implementation(project(":client:common"))
            }
        }
    }
}

compose.experimental {
    web.application {}
}
