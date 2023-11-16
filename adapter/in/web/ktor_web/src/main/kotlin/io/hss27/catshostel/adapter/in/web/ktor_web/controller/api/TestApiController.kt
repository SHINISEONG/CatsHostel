package io.hss27.catshostel.adapter.`in`.web.ktor_web.controller.api

import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Route.testApiController() {
    route("test") {
        get("testapi") {
            call.respond(mapOf("test" to "api"))
        }
    }
}