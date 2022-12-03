plugins {

    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    kotlin("plugin.allopen") apply false
    id("io.quarkus") apply false

    kotlin("android") apply false
    id("com.android.application") apply false version "7.2.2"
    id("com.android.library") apply false version "7.2.2"
    id("org.jetbrains.compose") apply false

}


gradle.taskGraph.whenReady {
    if (hasTask(":lib:build")) {
        allTasks.forEach {
            if (it.project.name == "client") {
                it.enabled = false
            }
        }
    }
}

gradle.taskGraph.whenReady {
    println(
        "${"Project".padEnd(16)} | ${"Task".padEnd(32)} | ${
            "Enabled".padEnd(
                16
            )
        } | ${"Class".padEnd(16)}"
    )

    allTasks.forEach {
        val project = it.project.name.padEnd(16)
        val enabled = it.enabled.toString().padEnd(16)
        val jclass = it.javaClass.simpleName.padEnd(16)
        val task = it.name.padEnd(32)
        println("$project | $task | $enabled | $jclass")
    }
}


allprojects {
    repositories {
        maven {
            url = uri("https://jitpack.io")
        }
    }
}
