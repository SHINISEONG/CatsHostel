package io.hss27.catshostel.bootstrap.di.spring_ioc_container

import io.hss27.catshostel.adapter.`in`.web.webflux_web.controller.api.CatApiController
import io.hss27.catshostel.application.domain.service.ReactiveCatManagementService
import io.hss27.catshostel.bootstrap.di.spring_ioc_container.serializers.Serializers
import io.hss27.catshostel.bootstrap.spring_transaction.TransactionalCatManagementService
import org.springframework.context.support.beans
import org.springframework.http.codec.ServerCodecConfigurer
import org.springframework.http.codec.json.KotlinSerializationJsonDecoder
import org.springframework.http.codec.json.KotlinSerializationJsonEncoder
import org.springframework.web.reactive.config.WebFluxConfigurer

val applicationBeans = beans {
    bean {
        ReactiveCatManagementService(
            catQueryRepository = ref(),
            catCommandRepository = ref()
        )
    }
    bean(isPrimary = true) {
        val catManagementService = ref<ReactiveCatManagementService>()
        TransactionalCatManagementService(
            catManagementService = catManagementService
        )
    }
}

val webBeans = beans {
    bean {
        object : WebFluxConfigurer {
            override fun configureHttpMessageCodecs(configurer: ServerCodecConfigurer) {
                configurer.defaultCodecs().apply {
                    kotlinSerializationJsonEncoder(KotlinSerializationJsonEncoder(Serializers.JSON))
                    kotlinSerializationJsonDecoder(KotlinSerializationJsonDecoder(Serializers.JSON))
                }
            }
        }
    }
    bean { CatApiController(catManagementService = ref()) }
}

