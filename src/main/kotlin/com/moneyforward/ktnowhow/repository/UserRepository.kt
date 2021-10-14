package com.moneyforward.ktnowhow.repository

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput

interface UserRepository {
    fun findUser(id: ID): User
    fun createUser(name: String, iconUrl: String? = null): User
    fun updateUser(user: UserInput): User
}