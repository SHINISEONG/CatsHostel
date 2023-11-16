package io.hss27.catshostel.application.port.`in`.usecase

import io.hss27.catshostel.application.port.`in`.command.ModifyCatCommand

fun interface ModifyCatUseCase {
    fun modify(command: ModifyCatCommand)
}