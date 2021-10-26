package com.moneyforward.ktnowhow.db.entity

import com.moneyforward.ktnowhow.db.table.Knowhows
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Knowhow(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<Knowhow>(Knowhows)

    var title: String by Knowhows.title
    var url: String by Knowhows.url
    val author by User referencedOn Knowhows.author
}