plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.3.12"
    id("io.spring.dependency-management") version "1.1.7"

    kotlin("plugin.serialization") version "1.8.0"
    kotlin("plugin.jpa") version "1.9.22"
}

group = "com.lecture"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

    //DB
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    //mongoDB
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.jetbrains.kotlin:kotlin-reflect")

    //OkhttpClient
    implementation("com.squareup.okhttp3:okhttp:4.12.0")

    //kotlin
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

    //jwt
    implementation("com.auth0:java-jwt:3.12.0")

    //ulid
    implementation("com.github.f4b6a3:ulid-creator:5.2.3")

    //redis
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.redisson:redisson-spring-boot-starter:3.36.0")

    //kafka
    implementation("org.springframework.kafka:spring-kafka:3.1.0")


    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
