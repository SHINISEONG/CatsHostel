val koin_ktor: String by project
plugins {
    kotlin("jvm")
    kotlin("plugin.spring")
    id("org.springframework.boot")
    id("io.spring.dependency-management")
}


dependencies {
    implementation(project(":adapter:in:web:webflux_web"))
    implementation(project(":adapter:out:persistence:r2dbc"))
    implementation(project(":bootstrap:di:spring_ioc_container"))
    implementation(project(":application"))
}
