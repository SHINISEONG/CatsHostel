package io.hss27.catshostel.bootstrap.ktor

import io.hss27.catshostel.bootstrap.ktor.plugins.configureDataSource
import io.hss27.catshostel.bootstrap.ktor.plugins.configureDi
import io.hss27.catshostel.bootstrap.ktor.plugins.configureRouting
import io.hss27.catshostel.bootstrap.ktor.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    configureDataSource()
    configureSerialization()
    configureDi()
    configureRouting()
}
