val koin_version: String by project
val koin_logger_slf4j: String by project
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application"))
    implementation(project(":adapter:in:web:ktor_web"))
    implementation(project(":adapter:out:persistence:exposed"))
    implementation(project(":bootstrap:transaction:exposed_transaction"))
    api("io.insert-koin:koin-logger-slf4j:$koin_logger_slf4j")
    api("io.insert-koin:koin-core:$koin_version")
}