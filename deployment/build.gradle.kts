group = "io.brule"
version = "alpha"

repositories {
    mavenCentral()
}

dependencies {

}

tasks.register("compose"){
    doLast{
        exec{
            workingDir = file("compose")
            commandLine("./scripts/roll.sh")
        }
    }
}