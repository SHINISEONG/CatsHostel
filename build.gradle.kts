import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val kotlin_version: String by project

plugins {
    kotlin("jvm") version "1.9.20" apply false
    kotlin("plugin.spring") version "1.9.20" apply false
    kotlin("plugin.serialization") version "1.9.20" apply false
    id("org.springframework.boot") version "3.1.5" apply false
    id("io.spring.dependency-management") version "1.1.4" apply false


}



allprojects {
    afterEvaluate {
        if (plugins.hasPlugin("org.jetbrains.kotlin.jvm")) {
            tasks.withType<KotlinCompile>().configureEach {
                kotlinOptions.jvmTarget = "17"
            }
        }
    }
    repositories {
        google()
        mavenCentral()
    }

    group = "io.hss27.catshostel"
}


