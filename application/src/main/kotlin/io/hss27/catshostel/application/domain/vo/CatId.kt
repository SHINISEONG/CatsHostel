package io.hss27.catshostel.application.domain.vo

import io.hss27.catshostel.application.domain.exceptions.CatExceptions

data class CatId(val value: Int) {
    init {
        require(value >= 0) { throw CatExceptions.valid("Id는 음수가 될 수 없습니다.") }
    }
}