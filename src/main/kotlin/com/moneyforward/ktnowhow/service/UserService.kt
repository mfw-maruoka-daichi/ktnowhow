package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.User
import com.moneyforward.ktnowhow.graphql.type.UserInput

interface UserService {
    fun findUserBy(id: ID): User?
    fun createUser(name: String, iconUrl: String? = null): User
    fun updateUser(user: UserInput): User
}