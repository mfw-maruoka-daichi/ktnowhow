package com.moneyforward.ktnowhow.db.entity

import com.moneyforward.ktnowhow.db.table.Tags
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TagEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<TagEntity>(Tags)

    var name: String by Tags.name
}