package io.hss27.catshostel.application.port.`in`.usecase

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.command.ModifyCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery

interface CatManagementUseCase {
    fun register(command: CreateCatCommand): Cat
    fun findById(query: FindCatQuery): Cat?
    fun findAll(): Cats
    fun modify(command: ModifyCatCommand): Cat
    fun delete(deleteCatCommand: DeleteCatCommand): Cat

}