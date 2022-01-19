package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.TagEntity
import com.moneyforward.ktnowhow.model.Tag
import org.springframework.stereotype.Repository

@Repository
class TagRepositoryImpl : TagRepository {
    override fun getAll(): List<Tag> = TagEntity.all().map { it.toTag() }
}

fun TagEntity.toTag(): Tag =
    Tag(
        id = id.value,
        name = name
    )
