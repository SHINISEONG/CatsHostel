package io.hss27.catshostel.adapter.out.persistence.exposed.impl

import io.hss27.catshostel.adapter.out.persistence.exposed.entity.CatEntity
import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.vo.Age
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.domain.vo.Name
import io.hss27.catshostel.application.port.out.CatRepository

class CatRepositoryImpl : CatRepository {
    override fun findByOrderByName(): Cats {
        return CatEntity.all().sortedBy { it.name }.toCats()
    }

    override fun findById(id: CatId): Cat? {
        return CatEntity.findById(id.value)?.toCat()
    }

    override fun save(cat: Cat) {
        CatEntity.new {
            name = cat.name.value
            age = cat.age.value
            species = cat.species
        }
    }

    override fun update(cat: Cat) {
        val catEntity = CatEntity.findById(cat.id.value)

        catEntity?.name = cat.name.value
        catEntity?.age = cat.age.value
        catEntity?.species = cat.species
    }

    override fun delete(id: CatId) {
        val catEntity = CatEntity.findById(id.value)
        catEntity?.delete()
    }
}

private fun List<CatEntity>.toCats(): Cats {
    return Cats(cats = map { it.toCat() })
}

private fun CatEntity.toCat(): Cat = Cat(
    id = CatId(id.value),
    name = Name(name),
    age = Age(age),
    species = species
)