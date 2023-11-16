package io.hss27.catshostel.adapter.`in`.web.ktor_web.controller.api

import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command.CreateCatCommandDto
import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command.ModifyCatCommandDto
import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.response.toCatResponseDto
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
        get("{id}") {
            val id = requireNotNull(call.parameters["id"]).toInt()
            val response = catManagementService.findById(FindCatQuery(CatId(id)))?.toCatResponseDto()
            call.respond(response ?: "Nothing")
        }

        post("") {
            val command = call.receive<CreateCatCommandDto>()
            catManagementService.register(command.toDomainCommand())
        }

        patch("") {
            val command = call.receive<ModifyCatCommandDto>()
            catManagementService.modify(command.toDomainCommand())
        }

        delete("{id}") {
            val id = requireNotNull(call.parameters["id"]).toInt()
            catManagementService.delete(DeleteCatCommand(CatId(id)))
        }
    }
}