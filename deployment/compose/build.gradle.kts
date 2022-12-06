// the scripts are located in ./scripts
// we need to create a new task for each script
// the names of the scripts are build.sh, clean.sh, down.sh, image.sh, nuke.sh, roll.sh, up.sh
// these commands control the build process using docker-compose

tasks.register<Exec>("build") {
    commandLine("./scripts/build.sh")
}

tasks.register<Exec>("cleanImages") {
    commandLine("./scripts/clean.sh")
}

tasks.register<Exec>("down") {
    commandLine("./scripts/down.sh")
}

tasks.register<Exec>("image") {
    commandLine("./scripts/image.sh")
}

tasks.register<Exec>("nuke") {
    commandLine("./scripts/nuke.sh")
}

tasks.register<Exec>("roll") {
    commandLine("./scripts/roll.sh")
}

tasks.register<Exec>("up") {
    commandLine("./scripts/up.sh")
}

// docker-compose logs -f <service>

tasks.register<Exec>("logs") {
    commandLine("./scripts/logs.sh")
}
