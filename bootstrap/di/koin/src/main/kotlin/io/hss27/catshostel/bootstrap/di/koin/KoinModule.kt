package io.hss27.catshostel.bootstrap.di.koin

import io.hss27.catshostel.adapter.out.persistence.exposed.impl.CatRepositoryImpl
import io.hss27.catshostel.application.domain.service.CatManagementService
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import io.hss27.catshostel.application.port.out.CatRepository
import io.hss27.catshostel.bootstrap.transaction.exposed_transaction.TransactionalCatManagementService
import org.koin.dsl.module

val koinModule = module {
    single<CatRepository> { CatRepositoryImpl() }
    single<CatManagementUseCase> { TransactionalCatManagementService(CatManagementService(get())) }
}
