package com.moneyforward.ktnowhow.db.entity

import com.moneyforward.ktnowhow.db.table.Knowhows
import com.moneyforward.ktnowhow.db.table.KnowhowsTags
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class KnowhowEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<KnowhowEntity>(Knowhows)

    var title: String by Knowhows.title
    var url: String by Knowhows.url
    var author by UserEntity referencedOn Knowhows.authorId
    var tags by TagEntity via KnowhowsTags
}