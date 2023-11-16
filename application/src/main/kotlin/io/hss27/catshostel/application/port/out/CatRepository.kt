package io.hss27.catshostel.application.port.out

import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId

interface CatRepository {
    fun findByOrderByName(): Cats
    fun findById(id: CatId): Cat?
    fun save(cat: Cat)
    fun update(cat: Cat)
    fun delete(id: CatId)

}