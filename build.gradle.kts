import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.5"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
    id("com.jetbrains.exposed.gradle.plugin") version "0.2.1"
}

group = "com.moneyforward"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

val graphqlKotlinVersion = "5.1.1"
val exposedVersion = "0.35.3"
val h2Version = "1.4.200"
val hikariCpVersion = "5.0.0"
val konformVersion = "0.3.0"
val kotestVersion = "4.6.3"
val mockkVersion = "1.12.1"

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.exposed:exposed-spring-boot-starter:$exposedVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("com.expediagroup:graphql-kotlin-spring-server:$graphqlKotlinVersion")
    implementation("org.flywaydb:flyway-core")
    implementation("com.h2database:h2:$h2Version")
    implementation("com.zaxxer:HikariCP:$hikariCpVersion")
    implementation("io.konform:konform-jvm:$konformVersion")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
    testImplementation("io.kotest:kotest-runner-junit5-jvm:$kotestVersion")
    testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
    testImplementation("io.mockk:mockk:$mockkVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

exposedCodeGeneratorConfig {
    configFilename = "exposed_generate_config.yml"
    connectionURL = "jdbc:h2:~/.h2/ktnowhow"
    user = "sa"
    password = ""
}

tasks.generateExposedCode {
    // 自動生成ファイルの上書きしないようなので
    dependsOn("clean")
}
