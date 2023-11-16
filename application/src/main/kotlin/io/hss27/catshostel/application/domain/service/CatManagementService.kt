package io.hss27.catshostel.application.domain.service

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.command.ModifyCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import io.hss27.catshostel.application.port.out.CatRepository

class CatManagementService(
    private val catRepository: CatRepository
) : CatManagementUseCase {
    override fun delete(deleteCatCommand: DeleteCatCommand) {
        catRepository.delete(CatId(deleteCatCommand.id.value))
    }

    override fun findById(query: FindCatQuery): Cat? = catRepository.findById(query.id)

    override fun findAll(): Cats = catRepository.findByOrderByName()

    override fun modify(command: ModifyCatCommand) {
        catRepository.update(
            Cat(
                id = command.id,
                name = command.name,
                age = command.age,
                species = command.species
            )
        )

    }

    override fun register(command: CreateCatCommand) {
        catRepository.save(
            Cat(
                id = CatId(0),
                name = command.name,
                age = command.age,
                species = command.species
            )
        )
    }
}