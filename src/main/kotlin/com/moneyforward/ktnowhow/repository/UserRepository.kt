package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.repository.common.SortOrder

interface UserRepository {
    fun getAll(): List<DefinedUser>
    fun fetch(cursor: Long, limit: Int, sortOrder: SortOrder): List<DefinedUser>
    fun findUserBy(id: Long): DefinedUser?
    fun upsertUser(user: User): DefinedUser
    fun deleteUser(id: Long): Long?
}