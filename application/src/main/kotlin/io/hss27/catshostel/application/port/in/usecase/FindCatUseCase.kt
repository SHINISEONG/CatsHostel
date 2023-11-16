package io.hss27.catshostel.application.port.`in`.usecase

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.port.`in`.query.FindCatQuery

interface FindCatUseCase {
    fun findById(query: FindCatQuery): Cat?
    fun findAll(): Cats
}