package io.hss27.catshostel.application.port.`in`.command

import io.hss27.catshostel.application.domain.vo.Age
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.domain.vo.Name

data class ModifyCatCommand(val id: CatId, val name: Name, val age: Age, val species: String)