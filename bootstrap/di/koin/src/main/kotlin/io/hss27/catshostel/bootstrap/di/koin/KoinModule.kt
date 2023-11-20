package io.hss27.catshostel.bootstrap.di.koin

import io.hss27.catshostel.adapter.out.persistence.exposed.impl.ExposedCatRepository
import io.hss27.catshostel.application.domain.service.CatManagementService
import io.hss27.catshostel.application.port.`in`.usecase.CatManagementUseCase
import io.hss27.catshostel.application.port.out.CatCommandRepository
import io.hss27.catshostel.application.port.out.CatQueryRepository
import io.hss27.catshostel.bootstrap.transaction.exposed_transaction.TransactionalCatManagementService
import org.koin.dsl.module

val koinModule = module {
    single<CatCommandRepository> { ExposedCatRepository() }
    single<CatQueryRepository> { ExposedCatRepository() }
    single<CatManagementUseCase> { TransactionalCatManagementService(CatManagementService(get(), get())) }
}
