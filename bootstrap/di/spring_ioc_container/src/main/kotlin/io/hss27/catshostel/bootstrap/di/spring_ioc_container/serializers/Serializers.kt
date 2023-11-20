package io.hss27.catshostel.bootstrap.di.spring_ioc_container.serializers

import kotlinx.serialization.json.Json

object Serializers {

    val JSON = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
        allowSpecialFloatingPointValues = true
        allowStructuredMapKeys = true
        prettyPrint = false
        useArrayPolymorphism = false
    }
}
