package com.moneyforward.ktnowhow.db.table

import org.jetbrains.exposed.dao.id.LongIdTable

object Users : LongIdTable() {
    val name = varchar("name", 30)

    // RFC上は無制限だが現実的に https://www.tyto-style.com/blog/archives/2725
    val iconUrl = varchar("icon_url", 2000).nullable()
}