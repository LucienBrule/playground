

plugins {

    kotlin("multiplatform") apply false
    kotlin("plugin.serialization") apply false
    kotlin("plugin.allopen") apply false
    id("io.quarkus") apply false
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


//tasks.withType<org.jetbrains.kotlin.gradle.targets.js.npm.tasks.RootPackageJsonTask> {
//    println("Found RootPackageJsonTask")
//}
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

tasks.register("hello"){
    doLast {
        println("Hello")
    }
}