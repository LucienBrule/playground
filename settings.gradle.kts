rootProject.name = "playground"
include("client")
include("server")
include("deployment")
include("lib")

pluginManagement{
    val kotlinVersion: String by settings
    val quarkusVersion: String by settings

    plugins{
        kotlin("multiplatform") version kotlinVersion
        kotlin("plugin.serialization") version kotlinVersion
        kotlin("plugin.allopen") version kotlinVersion
        id("io.quarkus") version quarkusVersion
    }

}