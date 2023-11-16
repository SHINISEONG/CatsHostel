package io.hss27.catshostel.adapter.out.persistence.exposed.entity

import io.hss27.catshostel.adapter.out.persistence.exposed.tables.CatTable
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class CatEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<CatEntity>(CatTable)

    var name by CatTable.name
    var age by CatTable.age
    var species by CatTable.species

}