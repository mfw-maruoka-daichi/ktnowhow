package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

object KnowhowsTags : LongIdTable("knowhows_tags") {
    val knowhowId = long("knowhow_id").index("idx_knowhow_id")
        .references(Knowhows.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
    val tagId = long("tag_id").index("idx_tag_id")
        .references(Tags.id, onUpdate = ReferenceOption.CASCADE, onDelete = ReferenceOption.CASCADE)
}