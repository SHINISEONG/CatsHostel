package io.hss27.catshostel.application.port.out

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId

interface ReactiveCatQueryRepository {
    suspend fun findByOrderById(): Cats
    suspend fun findById(id: CatId): Cat

}