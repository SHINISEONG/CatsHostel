package io.hss27.catshostel.application.domain.vo

data class Age(val value: Int) {
    init {
        require(value >= 0) { "나이는 음수가 될 수 없습니다." }
    }
}