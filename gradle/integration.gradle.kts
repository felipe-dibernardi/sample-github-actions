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

