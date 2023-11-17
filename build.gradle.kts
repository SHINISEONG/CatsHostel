val ktor_version: String by project
val kotlin_version: String by project
val logback_version: String by project
val exposed_version: String by project

plugins {
    kotlin("jvm") version "1.9.20" apply false
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }

    group = "io.hss27.catshostel"
}


