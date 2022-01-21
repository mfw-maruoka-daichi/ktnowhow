package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.KnowhowEntity
import com.moneyforward.ktnowhow.db.table.Knowhows
import com.moneyforward.ktnowhow.db.table.KnowhowsTags
import com.moneyforward.ktnowhow.db.table.Tags
import com.moneyforward.ktnowhow.model.Knowhow
import org.jetbrains.exposed.sql.batchInsert
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class KnowhowRepositoryImpl : KnowhowRepository {
    override fun getAll(): List<Knowhow> = KnowhowEntity.all().map { it.toKnowhow() }

    override fun addKnowhow(title: String, url: String, authorId: Long, tagIds: List<Long>): Knowhow {
        val existentIds =
            Tags.slice(Tags.id).select { Tags.id.inList(tagIds) }.map { it[Tags.id] }

        if (existentIds.size != tagIds.size) {
            val diff = tagIds.subtract(existentIds.map { it.value }.toSet())
            throw IllegalArgumentException("Tag.id:$diff not exist")
        }

        val knowhowId = Knowhows.insertAndGetId {
            it[this.title] = title
            it[this.url] = url
            it[this.authorId] = authorId
        }

        KnowhowsTags.batchInsert(tagIds) {
            this[KnowhowsTags.knowhowId] = knowhowId
            this[KnowhowsTags.tagId] = it
        }

        return KnowhowEntity.findById(knowhowId)!!.toKnowhow()
    }
}

fun KnowhowEntity.toKnowhow(): Knowhow =
    Knowhow(
        id = id.value,
        title = title,
        url = url,
        author = author.toUser(),
        tags = tags.map { it.toTag() }
    )
