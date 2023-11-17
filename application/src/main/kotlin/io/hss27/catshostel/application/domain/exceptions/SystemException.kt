package io.hss27.catshostel.application.domain.exceptions

abstract class SystemException(
    message: String,
    cause: Throwable? = null
) : RuntimeException(message, cause)