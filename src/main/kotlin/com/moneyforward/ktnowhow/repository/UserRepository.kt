package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.graphql.type.User
import com.moneyforward.ktnowhow.graphql.type.UserInput

interface UserRepository {
    fun findUserBy(id: Long): User?
    fun createUser(name: String, iconUrl: String? = null): User
    fun updateUser(user: UserInput): User
}