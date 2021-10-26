package com.moneyforward.ktnowhow.db.entity

import com.moneyforward.ktnowhow.db.table.Tags
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Tag(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Tag>(Tags)

    var name: String by Tags.name
}