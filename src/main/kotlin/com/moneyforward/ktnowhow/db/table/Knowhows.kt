package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object Knowhows : LongIdTable("knowhows") {
    val title = varchar("title", 50)
    val url = varchar("url", 2000)

    // referenceじゃないとダメらしい https://github.com/JetBrains/Exposed/issues/1141
    // Column<EntityID<>>で定義されないとダメっぽい
    val authorId =
        reference("author_id", Users.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
            .index("idx_author_id")
}