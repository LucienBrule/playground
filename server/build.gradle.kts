

description = "server"
group = "io.brule"
version = "alpha"




plugins {
    kotlin("jvm") version "1.7.10"
    kotlin("plugin.allopen") version "1.7.10"
    id("io.quarkus") version "2.13.0.Final"
}

repositories {
    mavenCentral()
    mavenLocal()
}

val quarkusPlatformGroupId: String by project
val quarkusPlatformArtifactId: String by project
val quarkusPlatformVersion: String by project

dependencies {
    implementation(enforcedPlatform("${quarkusPlatformGroupId}:${quarkusPlatformArtifactId}:${quarkusPlatformVersion}"))

    implementation("io.quarkus:quarkus-resteasy-reactive-jackson")
    implementation("io.quarkus:quarkus-resteasy-reactive")
    implementation("io.quarkus:quarkus-kotlin")
    implementation("io.quarkus:quarkus-resteasy-reactive-kotlin-serialization")
    implementation("io.quarkus:quarkus-arc")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")


    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:kotlin-extensions")


}

tasks.processResources{
    dependsOn(":client:build")
    val frontendBuildDir = project(":client").buildDir
    val frontendDistDir = frontendBuildDir.toPath().resolve("dist")
    from(frontendDistDir) {
        include("**/*")
        into("META-INF/resources")
    }
}

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
    kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
    kotlinOptions.javaParameters = true
}