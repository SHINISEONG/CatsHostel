package io.hss27.catshostel.application.port.out

import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.CatId

interface CatCommandRepository {
    fun save(cat: Cat): Cat
    fun update(cat: Cat): Cat
    fun delete(id: CatId): Cat

}