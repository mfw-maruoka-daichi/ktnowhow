package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.TagEntity
import com.moneyforward.ktnowhow.db.table.Tags
import com.moneyforward.ktnowhow.model.Tag
import org.jetbrains.exposed.sql.batchInsert
import org.springframework.stereotype.Repository

@Repository
class TagRepositoryImpl : TagRepository {
    override fun getAll(): List<Tag> = TagEntity.all().map { it.toTag() }

    override fun createTag(name: String): Tag = TagEntity.new { this.name = name }.toTag()

    override fun createTags(names: List<String>): List<Tag> =
        Tags.batchInsert(names) { this[Tags.name] = it }
            .map { row -> Tag(id = row[Tags.id].value, name = row[Tags.name]) }
}

fun TagEntity.toTag(): Tag =
    Tag(
        id = id.value,
        name = name
    )
