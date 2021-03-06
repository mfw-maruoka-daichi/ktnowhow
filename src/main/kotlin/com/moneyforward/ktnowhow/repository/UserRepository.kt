package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.common.PaginationDirection
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.User

interface UserRepository {
    fun getAll(): List<DefinedUser>
    fun fetch(cursor: Long, limit: Int, direction: PaginationDirection): List<DefinedUser>
    fun findUserBy(id: Long): DefinedUser?
    fun upsertUser(user: User): DefinedUser
    fun deleteUser(id: Long): Long?
}