package io.hss27.catshostel.application.domain.vo

import io.hss27.catshostel.application.domain.exceptions.CatExceptions

data class Name(val value: String) {
    init {
        require(value.isNotBlank()) { throw CatExceptions.valid("이름은 반드시 입력 해야 합니다.") }
    }
}