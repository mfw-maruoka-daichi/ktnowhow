package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.KnowhowEntity
import com.moneyforward.ktnowhow.db.entity.TagEntity
import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.db.table.Tags
import com.moneyforward.ktnowhow.model.Knowhow
import org.jetbrains.exposed.dao.exceptions.EntityNotFoundException
import org.jetbrains.exposed.dao.id.EntityID
import org.springframework.stereotype.Repository

@Repository
class KnowhowRepositoryImpl : KnowhowRepository {
    override fun getAll(): List<Knowhow> = KnowhowEntity.all().map { it.toKnowhow() }

    override fun addKnowhow(title: String, url: String, authorId: Long, tagIds: List<Long>): Knowhow {
        val tagEntities = TagEntity.forIds(tagIds)
        if (tagEntities.count() != tagIds.size.toLong()) {
            val diff = tagIds.subtract(tagEntities.map { it.id.value }.toSet()).first()
            throw EntityNotFoundException(EntityID(diff, Tags), TagEntity)
        }

        val knowhowEntity = KnowhowEntity.new {
            this.title = title
            this.url = url
            this.author = UserEntity[authorId]
        }

        // knowhowEntityを一度insertしたあとにtagsに設定しないと、Knowhows.idが存在しないのでinsert時に外部キーを解決できずエラーとなる
        // そのためtagなしでnew{}し、その後Entityをupdateするような書き方をすることで、KnowhowsTagsにinsertされる
        knowhowEntity.tags = tagEntities

        return knowhowEntity.toKnowhow()
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
