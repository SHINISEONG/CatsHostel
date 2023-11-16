package io.hss27.catshostel.application.domain.vo

data class Name(val value: String) {
    init {
        require(value.isNotBlank()) { "이름은 반드시 입력 해야 합니다." }
    }
}