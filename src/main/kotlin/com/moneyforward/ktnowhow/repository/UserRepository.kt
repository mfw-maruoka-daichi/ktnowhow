package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput

interface UserRepository {
    fun findUserBy(id: Long): User?
    fun createUser(name: String, iconUrl: String? = null): User
    fun updateUser(user: UserInput): User?
}