val koin_ktor: String by project
plugins {
    kotlin("jvm")
}


dependencies {
    implementation(project(":adapter:in:web:ktor_web"))
    implementation(project(":adapter:out:persistence:exposed"))
    implementation(project(":bootstrap:di:koin"))
    implementation("io.insert-koin:koin-ktor:3.5.1")
    implementation(project(":application"))
}
