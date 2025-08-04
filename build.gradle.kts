plugins {
    kotlin("jvm") version "2.0.10"
}

group = "kr.eme.semiTradeShop"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("kr.eme.semiMoney:SemiMoney:1.0-SNAPSHOT")
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

tasks.jar {
    archiveFileName = "${project.name}-${project.version}.jar"
    destinationDirectory = file("C:\\Users\\Home\\Desktop\\Develop\\minecraft\\Bukkit\\paper 1.21.4 (Semicolon Primary Colony)\\plugins")
    manifest {
        attributes["Main-Class" ] = "kr.eme.semiTrade.SemiTrade"
    }
}