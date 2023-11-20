package io.hss27.catshostel.application.domain.service

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.command.ModifyCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import io.hss27.catshostel.application.port.out.CatCommandRepository
import io.hss27.catshostel.application.port.out.CatQueryRepository

class CatManagementService(
    private val catCommandRepository: CatCommandRepository,
    private val catQueryRepository: CatQueryRepository
) : CatManagementUseCase {
    override fun findById(query: FindCatQuery): Cat = catQueryRepository.findById(query.id)

    override fun findAll(): Cats = catQueryRepository.findByOrderById()

    override fun modify(command: ModifyCatCommand): Cat {
        return catCommandRepository.update(
            Cat(
                id = command.id,
                name = command.name,
                age = command.age,
                species = command.species
            )
        )
    }

    override fun register(command: CreateCatCommand): Cat {
        return catCommandRepository.save(
            Cat(
                id = CatId(0),
                name = command.name,
                age = command.age,
                species = command.species
            )
        )
    }

    override fun delete(deleteCatCommand: DeleteCatCommand): Cat {
        return catCommandRepository.delete(deleteCatCommand.id)
    }
}