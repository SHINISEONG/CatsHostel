val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project

plugins {
    kotlin("jvm")
    kotlin("plugin.serialization")
}

dependencies {
    implementation(project(":application"))

    api("io.ktor:ktor-server-core-jvm:$ktor_version")
    api("io.ktor:ktor-server-content-negotiation-jvm:$ktor_version")
    api("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktor_version")
    api("io.ktor:ktor-server-cio-jvm:$ktor_version")
    api("ch.qos.logback:logback-classic:$logback_version")
}