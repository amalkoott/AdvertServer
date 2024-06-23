
val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm") version "1.9.23"
    id("io.ktor.plugin") version "2.3.9"
    kotlin("plugin.serialization") version "1.9.23"
}

group = "com.advert"
version = "0.0.1"

application {
    mainClass.set("com.advert.ApplicationKt")

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}
ktor{
    docker{
        jreVersion.set(JavaVersion.VERSION_17)
        localImageName.set("advert-docker-image")
        imageTag.set("0.0.1-advert-image")
        /*
        portMappings.set(listOf(
            io.ktor.plugin.features.DockerPortMapping(
                3000,
                8080,
                io.ktor.plugin.features.DockerPortMappingProtocol.TCP
            )
        ))
        */
        externalRegistry.set(
            io.ktor.plugin.features.DockerImageRegistry.dockerHub(
                appName = provider { "ktor-app" },
                username = providers.environmentVariable("DOCKER_HUB_USERNAME"), // setx DOCKER_HUB_USERNAME yourHubUsername
                password = providers.environmentVariable("DOCKER_HUB_PASSWORD") // setx DOCKER_HUB_PASSWORD yourHubPassword
            )
        )
    }
}

dependencies {
    implementation("io.ktor:ktor-server-core-jvm")
    implementation("io.ktor:ktor-server-netty-jvm")
    implementation("ch.qos.logback:logback-classic:$logback_version")
    testImplementation("io.ktor:ktor-server-tests-jvm")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlin_version")
    implementation("io.ktor:ktor-server-status-pages:$ktor_version")

    implementation("io.ktor:ktor-server-content-negotiation-jvm")
    implementation("io.ktor:ktor-serialization-kotlinx-json-jvm")
    testImplementation("io.ktor:ktor-server-test-host:$ktor_version")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("io.ktor:ktor-client-core:$ktor_version")
    implementation("io.ktor:ktor-client-cio:$ktor_version")
    implementation("io.ktor:ktor-client-auth:$ktor_version")

    implementation("org.jsoup:jsoup:1.17.2")

    implementation("org.seleniumhq.selenium:selenium-java:4.1.0")

    implementation("org.apache.httpcomponents.core5:httpcore5:5.2.1")
}
