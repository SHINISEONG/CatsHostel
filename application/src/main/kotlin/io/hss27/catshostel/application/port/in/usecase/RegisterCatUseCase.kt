package io.hss27.catshostel.application.port.`in`.usecase

import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand

interface RegisterCatUseCase {
    fun register(command: CreateCatCommand)
}