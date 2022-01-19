package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Knowhows : LongIdTable("knowhows") {
    val title = varchar("title", 50)
    val url = varchar("url", 2000)
    val authorId = long("author_id").index("idx_author_id")
        .references(Users.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
}