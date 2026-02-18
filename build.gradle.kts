plugins {
    kotlin("jvm") version "2.2.0"
    `maven-publish`
}

group = "kr.eme.prcShop"
version = "1.0.13"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://jitpack.io")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.21.4-R0.1-SNAPSHOT")
    compileOnly("com.github.mireu9275:PRCMoney:v1.0.3")
    compileOnly("com.github.mireu9275:PRCMission:v1.0.7")
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
    withSourcesJar() // KDoc(설명)을 보기 위함
}

tasks.jar {
    archiveFileName = "${project.name}-${project.version}.jar"
    // 네트워크 경로 지정 시 백슬래시(\)를 두 번씩 쓰거나, Raw String(""")을 사용해야 합니다.
    destinationDirectory = file("\\\\172.30.1.38\\공유폴더\\paper 1.21.4\\plugins")
    manifest {
        attributes["Main-Class" ] = "kr.eme.prcShop.PRCShop"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
