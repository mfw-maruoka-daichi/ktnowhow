package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.UserInputType
import com.moneyforward.ktnowhow.graphql.type.UserType

interface UserService {
    fun findUserById(id: ID): UserType?
    fun createUser(name: String, iconUrl: String? = null): UserType
    fun updateUser(user: UserInputType): UserType
    fun deleteUser(id: ID): ID
}