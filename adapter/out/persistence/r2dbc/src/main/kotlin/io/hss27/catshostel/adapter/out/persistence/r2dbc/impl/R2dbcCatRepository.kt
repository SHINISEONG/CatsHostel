package io.hss27.catshostel.adapter.out.persistence.r2dbc.impl

import io.hss27.catshostel.adapter.out.persistence.r2dbc.entity.CatEntity
import io.hss27.catshostel.adapter.out.persistence.r2dbc.entity.toEntity
import io.hss27.catshostel.application.domain.aggregate.Cats
import io.hss27.catshostel.application.domain.entity.Cat
import io.hss27.catshostel.application.domain.exceptions.CatExceptions
import io.hss27.catshostel.application.domain.vo.CatId
import io.hss27.catshostel.application.port.out.ReactiveCatQueryRepository
import io.hss27.catshostel.application.port.out.ReactiveCatCommandRepository
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.data.domain.Sort
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate
import org.springframework.data.relational.core.query.Criteria.where
import org.springframework.data.relational.core.query.Query.empty
import org.springframework.data.relational.core.query.Query.query


class R2dbcCatRepository(
    private val entityTemplate: R2dbcEntityTemplate
) : ReactiveCatQueryRepository, ReactiveCatCommandRepository {
    private val entityClass = CatEntity::class.java
    override suspend fun save(cat: Cat): Cat =
        entityTemplate.insert(cat.toEntity()).awaitSingle().toDomain()

    override suspend fun update(cat: Cat): Cat =
        try {
            entityTemplate.update(cat.toEntity()).awaitSingle().toDomain()
        } catch (e: Exception) {
            throw e
        }


    override suspend fun delete(id: CatId): Cat =
        loadById(id).run {
            entityTemplate.delete(this).awaitSingle().toDomain()
        }

    override suspend fun findByOrderById(): Cats = Cats(
        entityTemplate
            .select(empty().sort(Sort.by(Sort.Order.asc("id"))), entityClass)
            .map { it.toDomain() }
            .asFlow()
            .toList()
    )

    override suspend fun findById(id: CatId): Cat = loadById(id).toDomain()

    private suspend fun loadById(id: CatId): CatEntity =
        try {
            entityTemplate
                .selectOne(query(where("id").`is`(id.value)), entityClass).awaitSingle()
        } catch (e: Exception) {
            throw CatExceptions.notFound(id)
        }
}