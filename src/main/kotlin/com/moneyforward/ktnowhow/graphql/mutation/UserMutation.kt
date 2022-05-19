package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.UserPropertyType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.service.UserService
import org.springframework.stereotype.Component

@Component
class UserMutation(private val userService: UserService) : Mutation {
    fun createUser(userProperty: UserPropertyType): UserType = userService.createUser(userProperty)
    fun updateUser(id: ID, userProperty: UserPropertyType): UserType = userService.updateUser(id, userProperty)
    fun deleteUser(id: ID): ID = userService.deleteUser(id)
}