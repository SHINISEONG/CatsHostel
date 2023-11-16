package io.hss27.catshostel.bootstrap.ktor.plugins

import io.hss27.catshostel.bootstrap.di.koin.koinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun Application.configureDi() {
    install(Koin) {
        slf4jLogger()
        modules(koinModule)
    }
}


