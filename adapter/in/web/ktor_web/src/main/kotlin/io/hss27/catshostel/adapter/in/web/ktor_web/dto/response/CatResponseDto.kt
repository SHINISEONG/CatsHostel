package io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.response

import io.hss27.catshostel.application.domain.entity.Cat
import kotlinx.serialization.Serializable

@Serializable
data class CatResponseDto(
    val id: Int,
    val name: String,
    val age: Int,
    val species: String = ""
)

fun Cat.toCatResponseDto(): CatResponseDto = CatResponseDto(
    id = this.id.value,
    name = this.name.value,
    age = this.age.value,
    species = this.species
)

