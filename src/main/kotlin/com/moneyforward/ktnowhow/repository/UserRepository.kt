package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.model.DefinedID
import com.moneyforward.ktnowhow.model.ID
import com.moneyforward.ktnowhow.model.User

interface UserRepository {
    fun findUserBy(id: Long): User<DefinedID>?
    fun upsertUser(user: User<ID>): User<DefinedID>
    fun deleteUser(id: Long): Long?
}