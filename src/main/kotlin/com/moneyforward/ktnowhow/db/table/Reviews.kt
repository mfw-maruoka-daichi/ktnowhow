package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Reviews : LongIdTable() {
    val knowhow = reference("knowhow", Knowhows)
    val rate = integer("rate")
    val comment = varchar("comment", 140).nullable()
    val author = reference("author", Users)
}