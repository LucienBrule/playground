plugins {
    kotlin("multiplatform") version "1.7.10" apply false
    kotlin("plugin.serialization") version "1.7.10" apply false
    kotlin("plugin.allopen") version "1.7.10" apply false
    id("io.quarkus") version "2.13.2.Final" apply false
}

gradle.taskGraph.whenReady {
    if (hasTask(":lib:build")) {
        println("Found that shit lib:build")
        allTasks.forEach {
            if (it.project.name == "client") {
                it.enabled = false
            }
        }
    }
}


tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.RootPackageJsonTask> {
    println("Found RootPackageJsonTask")
}
//
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
