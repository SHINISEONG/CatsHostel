val spring_boot_version: String by project
val coroutines_version: String by project
plugins {
    java
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
}
//apply(plugin = "io.spring.dependency-management")


dependencies {
    implementation(project(":application"))
    api("org.springframework.data:spring-data-r2dbc:$spring_boot_version")
    implementation("io.r2dbc:r2dbc-mssql:1.0.2.RELEASE")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-debug:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutines_version")


}