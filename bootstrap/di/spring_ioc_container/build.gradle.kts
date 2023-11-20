import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val koin_version: String by project
val koin_logger_slf4j: String by project
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.serialization")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}
tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":application"))
    implementation(project(":adapter:in:web:webflux_web"))
    implementation(project(":adapter:out:persistence:r2dbc"))
    implementation(project(":bootstrap:transaction:spring_transaction"))
}