package com.moneyforward.ktnowhow.db.entity

import com.moneyforward.ktnowhow.db.table.Users
import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserEntity(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<UserEntity>(Users)

    var name: String by Users.name
    var iconUrl: String? by Users.iconUrl
}