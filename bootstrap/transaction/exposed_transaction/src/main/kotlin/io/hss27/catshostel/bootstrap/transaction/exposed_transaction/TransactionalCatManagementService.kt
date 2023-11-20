package io.hss27.catshostel.bootstrap.transaction.exposed_transaction

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand
import io.hss27.catshostel.application.port.`in`.command.DeleteCatCommand
import io.hss27.catshostel.application.port.`in`.command.ModifyCatCommand
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import org.jetbrains.exposed.sql.transactions.transaction

class TransactionalCatManagementService(private val catManagementService: CatManagementUseCase) : CatManagementUseCase {

    override fun delete(deleteCatCommand: DeleteCatCommand): Cat {
        val result = catManagementService.delete(deleteCatCommand)
        return transaction { result }
    }


    override fun findById(query: FindCatQuery): Cat = transaction {
        catManagementService.findById(query)
    }

    override fun findAll(): Cats = transaction {
        catManagementService.findAll()
    }

    override fun modify(command: ModifyCatCommand) = transaction {
        catManagementService.modify(command)
    }

    override fun register(command: CreateCatCommand) = transaction {
        catManagementService.register(command)
    }
}