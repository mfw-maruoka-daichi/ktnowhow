package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.KnowhowEntity
import com.moneyforward.ktnowhow.db.entity.TagEntity
import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.model.Knowhow
import org.springframework.stereotype.Repository

@Repository
class KnowhowRepositoryImpl : KnowhowRepository {
    override fun getAll(): List<Knowhow> = KnowhowEntity.all().map { it.toKnowhow() }

    override fun addKnowhow(title: String, url: String, authorId: Long, tags: List<Long>?): Knowhow {
        val tmp = KnowhowEntity.new {
            this.title = title
            this.url = url
            this.author = UserEntity[authorId]
            tags?.let { this.tags = TagEntity.forIds(it) }
        }

        return tmp.toKnowhow()
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
