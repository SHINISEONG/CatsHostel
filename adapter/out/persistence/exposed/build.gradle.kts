val exposed_version: String by project
val mssql_jdbc_version: String by project
plugins {
    kotlin("jvm")
}

dependencies {
    implementation(project(":application"))

    api("org.jetbrains.exposed:exposed-core:$exposed_version")
    api("org.jetbrains.exposed:exposed-jdbc:$exposed_version")

//    implementation("mysql:mysql-connector-java:$mysql_jdbc_version")
    implementation("com.microsoft.sqlserver:mssql-jdbc:$mssql_jdbc_version")
    implementation("org.jetbrains.exposed:exposed-crypt:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-dao:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-json:$exposed_version")
    implementation("org.jetbrains.exposed:exposed-money:$exposed_version")
}