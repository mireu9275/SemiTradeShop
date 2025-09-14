plugins {
    kotlin("jvm") version "2.2.0"
}

group = "kr.eme.semiTradeShop"
version = "1.0.5"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.mireu9275:SemiMoneyGlobal:v1.0.1")
    compileOnly("com.github.mireu9275:semiMission:v1.0.5")
    compileOnly(kotlin("reflect"))
    compileOnly(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}
java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(21))
}

//tasks.jar {
//    archiveFileName = "${project.name}-${project.version}.jar"
//    destinationDirectory = file("C:\\Users\\Home\\Desktop\\Develop\\minecraft\\Bukkit\\paper 1.21.4 (Semicolon Primary Colony)\\plugins")
//    manifest {
//        attributes["Main-Class" ] = "kr.eme.semiTradeShop.SemiTradeShop"
//    }
//}
