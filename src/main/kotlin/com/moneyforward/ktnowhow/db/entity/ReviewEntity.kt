package com.moneyforward.ktnowhow.db.entity

import com.moneyforward.ktnowhow.db.table.Reviews
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class ReviewEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<ReviewEntity>(Reviews)

    val knowhow by KnowhowEntity referencedOn Reviews.knowhow
    var rate: Int by Reviews.rate
    var comment: String? by Reviews.comment
    val author by UserEntity referencedOn Reviews.author
}