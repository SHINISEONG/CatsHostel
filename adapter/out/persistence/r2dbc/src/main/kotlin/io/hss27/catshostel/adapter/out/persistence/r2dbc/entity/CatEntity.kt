package io.hss27.catshostel.adapter.out.persistence.r2dbc.entity

import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.Age
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.domain.vo.Name
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.PersistenceCreator
import org.springframework.data.relational.core.mapping.Table

@Table("cat")
class CatEntity @PersistenceCreator constructor(
    @Id
    var id: Int = 0,
    private val age: Int,
    private val name: String,
    private val species: String
) {
    fun toDomain(): Cat = Cat(
        id = CatId(id),
        name = Name(name),
        age = Age(age),
        species = species
    )


    override fun toString(): String {
        return "CatEntity(id=$id, age=$age, name='$name', species='$species')"
    }
}

fun Cat.toEntity(): CatEntity = CatEntity(
    id = id.value,
    name = name.value,
    age = age.value,
    species = species
)



