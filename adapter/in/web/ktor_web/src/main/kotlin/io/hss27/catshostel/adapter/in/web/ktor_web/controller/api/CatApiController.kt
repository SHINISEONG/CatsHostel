package io.hss27.catshostel.adapter.`in`.web.ktor_web.controller.api

import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command.CreateCatCommandDto
import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command.ModifyCatCommandDto
import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.response.toCatResponseDto
import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.response.toCatsResponseDto
import io.hss27.catshostel.application.domain.exceptions.CatException
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.catApiController(catManagementService: CatManagementUseCase) {
    route("cats") {
        get("") {
            call.respond(catManagementService.findAll().toCatsResponseDto())
        }

        get("{id}") {
            try {
                val id = requireNotNull(call.parameters["id"]).toInt()
                val response = catManagementService.findById(FindCatQuery(CatId(id))).toCatResponseDto()
                call.respond(response)
            } catch (exception: CatException) {
                call.respondText { exception.message.toString() }
            }
        }

        post("") {
            try {
                val command = call.receive<CreateCatCommandDto>()
                val response = catManagementService.register(command.toDomainCommand()).toCatResponseDto()
                call.respond(response)
            } catch (exception: CatException) {
                call.respondText { exception.message.toString() }
            }
        }

        patch("{id}") {
            try {
                val id = requireNotNull(call.parameters["id"]).toInt()
                val command = call.receive<ModifyCatCommandDto>().copy(id = id)
                val response = catManagementService.modify(command.toDomainCommand()).toCatResponseDto()
                call.respond(response)
            } catch (exception: CatException) {
                call.respondText { exception.message.toString() }
            }
        }

        delete("{id}") {
            try {
                val id = requireNotNull(call.parameters["id"]).toInt()
                val response = catManagementService.delete(DeleteCatCommand(CatId(id))).toCatResponseDto()
                call.respond(response)
            } catch (exception: CatException) {
                call.respondText { exception.message.toString() }
            }
        }
    }
}