package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.UserConnection
import com.moneyforward.ktnowhow.graphql.type.UserPropertyType
import com.moneyforward.ktnowhow.graphql.type.UserType

interface UserService {
    fun users(first: Int?, after: String?, last: Int?, before: String?): UserConnection
    fun findUserById(id: ID): UserType?
    fun createUser(userProperty: UserPropertyType): UserType
    fun updateUser(id: ID, userProperty: UserPropertyType): UserType
    fun deleteUser(id: ID): ID
}