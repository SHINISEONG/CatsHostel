package io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.request.command

import io.hss27.catshostel.application.domain.vo.Age
import io.hss27.catshostel.application.domain.vo.Name
import io.hss27.catshostel.application.port.`in`.command.CreateCatCommand
import kotlinx.serialization.Serializable

@Serializable
data class CreateCatCommandDto(val name: String, val age: Int, val species: String) {
    fun toDomainCommand(): CreateCatCommand = CreateCatCommand(
        name = Name(name),
        age = Age(age),
        species = species
    )
}
