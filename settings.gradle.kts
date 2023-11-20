rootProject.name = "CatsHostel"

include(":application")

include(":adapter:in:web:ktor_web")
include(":adapter:in:web:webflux_web")

include(":adapter:out:persistence:exposed")
include(":adapter:out:persistence:r2dbc")

include(":bootstrap:ktor")
include(":bootstrap:di:koin")
include(":bootstrap:transaction:exposed_transaction")

include(":bootstrap:spring")
include(":bootstrap:di:spring_ioc_container")
include(":bootstrap:transaction:spring_transaction")
