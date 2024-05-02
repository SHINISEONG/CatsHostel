package io.hss27.catshostel.application.port.out

import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId

interface ReactiveCatCommandRepository {
    suspend fun save(cat: Cat): Cat
    suspend fun update(cat: Cat): Cat
    suspend fun delete(id: CatId): Cat

}