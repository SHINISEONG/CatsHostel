package io.hss27.catshostel.adapter.`in`.web.ktor_web.dto.response

import io.hss27.catshostel.application.domain.aggregate.Cats
import kotlinx.serialization.Serializable

@Serializable
class CatsResponseDto(
    val cats: List<CatResponseDto> = emptyList(),
    val numberOfCat: Int = 0
)

fun Cats.toCatsResponseDto(): CatsResponseDto {
    return CatsResponseDto(
        cats = this.cats.map { it.toCatResponseDto() }.toList(),
        numberOfCat = this.count()
    )
}