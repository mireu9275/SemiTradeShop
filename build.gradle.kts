plugins {
    kotlin("jvm") version "2.2.0"
    `maven-publish`
}

group = "kr.eme.prcShop"
version = "1.1.1"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.mireu9275:PRCMoney:v1.0.3")
    compileOnly("com.github.mireu9275:PRCMission:v1.0.14")
    compileOnly(kotlin("reflect"))
    compileOnly(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
    withSourcesJar() // KDoc(설명)을 보기 위함
}

tasks.jar {
    archiveFileName = "${project.name}-${project.version}.jar"
    manifest {
        attributes["Main-Class"] = "kr.eme.prcShop.PRCShop"
    }
}

tasks.register<Copy>("copyToDesktop") {
    from(tasks.jar)
    into(File(System.getProperty("user.home"), "Desktop"))
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}