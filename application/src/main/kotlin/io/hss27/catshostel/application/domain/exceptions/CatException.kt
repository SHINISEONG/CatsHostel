package io.hss27.catshostel.application.domain.exceptions

import io.hss27.catshostel.application.domain.vo.CatId

sealed class CatException(
    message: String,
    cause: Throwable? = null
) : SystemException(message = message, cause = cause) {
    class CatValidException(message: String) : CatException(message)

    class CatNotFoundException(message: String) : CatException(message) {
        companion object {
            fun of(id: CatId) = CatNotFoundException("등록된 고양이가 없습니다.(id: $id)")
        }
    }
}

object CatExceptions {
    fun valid(message: String) = CatException.CatValidException(message)
    fun notFound(id: CatId) = CatException.CatNotFoundException.of(id)
}