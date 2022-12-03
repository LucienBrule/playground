package playground.utils

import java.io.File
import java.util.concurrent.TimeUnit


// a wrapper around bash that prints the output to the console and returns it
fun bash(command: String, workingDir: File): String {
    val process = ProcessBuilder("bash", "-c", command)
        .directory(workingDir)
        .redirectOutput(ProcessBuilder.Redirect.INHERIT)
        .redirectError(ProcessBuilder.Redirect.INHERIT)
        .start()

    process.waitFor(10, TimeUnit.MINUTES)

    return process.inputStream.bufferedReader().readText()
}
