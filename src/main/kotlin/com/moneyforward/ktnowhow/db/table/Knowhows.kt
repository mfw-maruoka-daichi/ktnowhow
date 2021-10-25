package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Knowhows : LongIdTable() {
    val title = varchar("title", 50)
    val url = varchar("url", 2000)
    val author = reference("author", Users)
}