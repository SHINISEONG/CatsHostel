package io.hss27.catshostel.bootstrap.ktor.plugins

import io.hss27.catshostel.adapter.`in`.web.ktor_web.controller.api.catApiController
import io.hss27.catshostel.adapter.`in`.web.ktor_web.controller.api.testApiController
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureRouting() {

    val catManagementService by inject<CatManagementUseCase>()

    routing {
        route("/api/v1") {
            testApiController()
            catApiController(catManagementService)
        }
        get("/") {
            call.respondText("Hello World!")
        }
    }
}
