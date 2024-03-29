description = "server"
group = "io.brule"
version = "alpha"

plugins {
    kotlin("jvm")
    kotlin("plugin.allopen")
    kotlin("plugin.serialization")
    id("io.quarkus")
    id("java")
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project
val ktorVersion: String by project
val ktxVersion: String by project

dependencies {

    implementation("io.brule:lib:alpha")
//    implementation(project(":lib"))
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
    implementation("io.quarkus:quarkus-arc")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.quarkus:quarkus-vertx")
    implementation("io.quarkus:quarkus-smallrye-reactive-messaging")
    implementation("io.quarkus:quarkus-websockets")


    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-resources:$ktorVersion")
    implementation("io.ktor:ktor-client-cio:$ktorVersion")
    implementation("io.ktor:ktor-client-auth:$ktorVersion")

    api(group = "io.github.libktx", name = "ktx-box2d", version = ktxVersion)
    api(group = "com.badlogicgames.gdx", name = "gdx-box2d")





    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:kotlin-extensions")


}

//tasks.processResources{
//    dependsOn(":client:build")
//    val frontendBuildDir = project(":client").buildDir
//    val frontendDistDir = frontendBuildDir.toPath().resolve("dist")
//    from(frontendDistDir) {
//        include("**/*")
//        into("META-INF/resources")
//    }
//}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

allOpen {
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    kotlinOptions.javaParameters = true
}
