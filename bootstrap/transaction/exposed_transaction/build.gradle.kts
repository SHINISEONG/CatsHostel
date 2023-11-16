plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":adapter:out:persistence:exposed"))
    implementation(project(":application"))
}