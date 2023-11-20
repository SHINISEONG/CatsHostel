package io.hss27.catshostel.bootstrap.spring_transaction

import io.hss27.catshostel.application.port.`in`.usecase.ReactiveCatManagementUseCase
import org.springframework.transaction.annotation.Transactional

@Transactional
class TransactionalCatManagementService(
    private val catManagementService: ReactiveCatManagementUseCase
) : ReactiveCatManagementUseCase by catManagementService
