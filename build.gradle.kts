import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.4"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.10"
    kotlin("plugin.spring") version "1.6.10"
    idea
}

group = "br.com.fdbst.sample"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

//apply(from = "${rootProject.projectDir}/gradle/integration.gradle.kts")

//apply(from = "gradle/integration.gradle.kts")

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.apache.tinkerpop:gremlin-core:3.5.2")
    implementation("org.apache.tinkerpop:gremlin-driver:3.5.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
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

val testIntegrationImplementation by configurations {
    create("testIntegrationImplementation").apply {
        extendsFrom(configurations["testImplementation"])
    }
}

idea.module {
    testSourceDirs.add(file("src/testIntegration/kotlin"))
    testResourceDirs.add(file("src/testIntegration/resources"))
}

sourceSets {
    create("testIntegration") {
        kotlin {
            compileClasspath += main.get().output + configurations.testRuntimeClasspath
            runtimeClasspath += output + compileClasspath
        }
    }
}

dependencies {
    testIntegrationImplementation("org.springframework.security:spring-security-test")
    testIntegrationImplementation("org.springframework.cloud:spring-cloud-contract-wiremock:2.2.2.RELEASE")
}


task<Test>("integration") {
    description = "Runs the integration tests"
    group = "verification"
    testClassesDirs = sourceSets["testIntegration"].output.classesDirs
    classpath = sourceSets["testIntegration"].runtimeClasspath
    mustRunAfter(tasks["test"])
}

