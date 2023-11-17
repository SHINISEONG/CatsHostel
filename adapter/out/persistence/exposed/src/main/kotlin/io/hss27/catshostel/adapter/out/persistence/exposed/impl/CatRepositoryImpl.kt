package io.hss27.catshostel.adapter.out.persistence.exposed.impl

import io.hss27.catshostel.adapter.out.persistence.exposed.entity.CatEntity
import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.exceptions.CatExceptions
import io.hss27.catshostel.application.domain.vo.Age
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.domain.vo.Name
import io.hss27.catshostel.application.port.out.CatCommandRepository
import io.hss27.catshostel.application.port.out.CatQueryRepository

class CatRepositoryImpl : CatCommandRepository, CatQueryRepository {
    override fun findByOrderById(): Cats {
        return CatEntity.all().sortedBy { it.id }.toCats()
    }

    override fun findById(id: CatId): Cat {
        return loadById(id).toCat()
    }

    override fun save(cat: Cat): Cat =
        CatEntity.new {
            name = cat.name.value
            age = cat.age.value
            species = cat.species
        }.toCat()

    override fun update(cat: Cat): Cat {
        val catEntity = loadById(cat.id)

        catEntity.name = cat.name.value
        catEntity.age = cat.age.value
        catEntity.species = cat.species

        return catEntity.toCat()
    }

    override fun delete(id: CatId): Cat {
        val catEntity = loadById(id)
        val cat = catEntity.toCat()

        catEntity.delete()

        return cat
    }

    private fun loadById(id: CatId): CatEntity {
        return CatEntity.findById(id.value) ?: throw CatExceptions.notFound(id)
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

}


