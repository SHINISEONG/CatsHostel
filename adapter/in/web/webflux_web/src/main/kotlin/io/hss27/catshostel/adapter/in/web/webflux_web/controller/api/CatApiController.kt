package io.hss27.catshostel.adapter.`in`.web.webflux_web.controller.api

import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command.CreateCatCommandDto
import io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command.ModifyCatCommandDto
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery
import io.hss27.catshostel.application.port.`in`.usecase.ReactiveCatManagementUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.reactive.function.server.*
import reactor.core.publisher.Mono

class CatApiController(
    private val catManagementService: ReactiveCatManagementUseCase
) : RouterFunction<ServerResponse> {
    private val delegate = coRouter {
        "/api/v1/cats".nest {
            GET("") {
                ok().bodyValueAndAwait(catManagementService.findAll())
            }

            GET("/{id}") { request ->
                try {
                    val id = CatId(request.pathVariable("id").toInt())
                    val query = FindCatQuery(id)
                    ok().bodyValueAndAwait(catManagementService.findById(query))
                } catch (e: Exception) {
                    status(HttpStatus.NOT_FOUND).bodyValueAndAwait(e.message ?: "Unknown Error")
                }
            }

            POST("") { request ->
                try {
                    val command = request.awaitBody<CreateCatCommandDto>()
                    ok().bodyValueAndAwait(catManagementService.register(command.toDomainCommand()))
                } catch (e: Exception) {
                    status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValueAndAwait(e.message ?: "Unknown Error")
                }

            }

            PATCH("/{id}") { request ->
                try {
                    val id = request.pathVariable("id").toInt()
                    val command = request.awaitBody<ModifyCatCommandDto>().run { this.copy(id = id) }
                    ok().bodyValueAndAwait(catManagementService.modify(command.toDomainCommand()))
                } catch (e: Exception) {
                    status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValueAndAwait(e.message ?: "Unknown Error")
                }
            }

            DELETE("/{id}") { request ->
                try {
                    val id = CatId(request.pathVariable("id").toInt())
                    val command = DeleteCatCommand(id)
                    ok().bodyValueAndAwait(catManagementService.delete(command))
                } catch (e: Exception) {
                    status(HttpStatus.INTERNAL_SERVER_ERROR).bodyValueAndAwait(e.message ?: "Unknown Error")
                }

            }
        }
    }

    override fun route(request: ServerRequest): Mono<HandlerFunction<ServerResponse>> = delegate.route(request)
}