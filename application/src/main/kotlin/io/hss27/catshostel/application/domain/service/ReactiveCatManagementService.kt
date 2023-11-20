package io.hss27.catshostel.application.domain.service

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.command.ModifyCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery
import io.hss27.catshostel.application.port.`in`.usecase.ReactiveCatManagementUseCase
import io.hss27.catshostel.application.port.out.ReactiveCatQueryRepository
import io.hss27.catshostel.application.port.out.ReativeCatCommandRepository

class ReactiveCatManagementService(
    private val catCommandRepository: ReativeCatCommandRepository,
    private val catQueryRepository: ReactiveCatQueryRepository
) : ReactiveCatManagementUseCase {
    override suspend fun findById(query: FindCatQuery): Cat = catQueryRepository.findById(query.id)

    override suspend fun findAll(): Cats = catQueryRepository.findByOrderById()

    override suspend fun modify(command: ModifyCatCommand): Cat {
        return catCommandRepository.update(
            Cat(
                id = command.id,
                name = command.name,
                age = command.age,
                species = command.species
            )
        )
    }

    override suspend fun register(command: CreateCatCommand): Cat {
        return catCommandRepository.save(
            Cat(
                id = CatId(0),
                name = command.name,
                age = command.age,
                species = command.species
            )
        )
    }

    override suspend fun delete(deleteCatCommand: DeleteCatCommand): Cat {
        return catCommandRepository.delete(CatId(deleteCatCommand.id.value))
    }
}