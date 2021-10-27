package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Tags : LongIdTable() {
    val name = varchar("name", 30)
}