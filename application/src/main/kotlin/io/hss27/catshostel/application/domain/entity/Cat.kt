package io.hss27.catshostel.application.domain.entity

import io.hss27.catshostel.application.domain.vo.Age
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.domain.vo.Name

data class Cat(
    val id: CatId,
    val name: Name,
    val age: Age,
    val species: String
) {

    fun update(
        name: Name = this.name,
        age: Age = this.age,
        species: String = this.species
    ): Cat = copy(
        name = name,
        age = age,
        species = species
    )

    override fun equals(other: Any?) = when {
        this === other -> true
        other is Cat -> other.id == id
        else -> false
    }

    override fun hashCode() = id.hashCode()

}