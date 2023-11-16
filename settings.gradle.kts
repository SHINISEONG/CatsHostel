rootProject.name = "CatsHostel"

include(":common")
include(":application")
include(":adapter:in:web:ktor_web")
include(":adapter:out:persistence:exposed")
include(":bootstrap:ktor")
include(":bootstrap:di:koin")
include(":bootstrap:transaction:exposed_transaction")