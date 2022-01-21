package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.ReferenceOption

// TableでknowhowIdとtagIdの複合主キーにすると、ExposedだとEntity作れない
// https://github.com/JetBrains/Exposed/issues/964
object KnowhowsTags : LongIdTable("knowhows_tags") {
    val knowhowId = reference(
        "knowhow_id",
        Knowhows.id,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE
    ).index("idx_knowhow_id")

    val tagId = reference(
        "tag_id",
        Tags.id,
        onUpdate = ReferenceOption.CASCADE,
        onDelete = ReferenceOption.CASCADE
    ).index("idx_tag_id")

    override val primaryKey = PrimaryKey(knowhowId, tagId)
}