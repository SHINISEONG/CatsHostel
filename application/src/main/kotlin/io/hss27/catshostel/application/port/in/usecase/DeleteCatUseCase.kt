package io.hss27.catshostel.application.port.`in`.usecase

import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand

fun interface DeleteCatUseCase {
    fun delete(deleteCatCommand: DeleteCatCommand)
}