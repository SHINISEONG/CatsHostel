package io.hss27.catshostel.bootstrap.ktor.plugins

import io.hss27.catshostel.adapter.out.persistence.exposed.tables.CatTable
import io.ktor.server.application.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.configureDataSource() {
    DatabaseFactory.init()
}


object DatabaseFactory {
    fun init() {
        val driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
        val jdbcURL =
            "jdbc:sqlserver://localhost;databaseName=cat_database;trustServerCertificate=true"
        val database = Database.connect(
            url = jdbcURL,
            driver = driverClassName,
            user = "imtsoft",
            password = "Imt050311"
        )
        transaction(database) {
            SchemaUtils.create(CatTable)
        }
    }

    suspend fun <T> dbQuery(block: suspend () -> T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }
}