package io.hss27.catshostel.application.domain.aggregate

import io.hss27.catshostel.application.domain.entity.Cat

data class Cats(
    private val cats: List<Cat> = emptyList()
) : List<Cat> by cats {
    fun count() = cats.size
}