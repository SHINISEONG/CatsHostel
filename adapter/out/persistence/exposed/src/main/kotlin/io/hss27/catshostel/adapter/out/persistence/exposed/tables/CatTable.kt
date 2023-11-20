package io.hss27.catshostel.adapter.out.persistence.exposed.tables

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime

object CatTable : IntIdTable("cats") {
    val name = varchar("name", 50)
    val age = integer("age")
    val species = varchar("species", 50)
    val createdAt = datetime("created_at").clientDefault { LocalDateTime.now() }
    val updatedAt = datetime("updated_at").nullable()
}