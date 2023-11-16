package io.hss27.catshostel.adapter.out.persistence.exposed.tables

import org.jetbrains.exposed.dao.id.IntIdTable

object CatTable : IntIdTable() {
    val name = varchar("name", 50)
    val age = integer("age")
    val species = varchar("species", 50)
}