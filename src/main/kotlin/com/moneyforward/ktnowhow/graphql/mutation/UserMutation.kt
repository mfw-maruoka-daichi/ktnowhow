package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.UserPropertyType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.service.UserService
import org.springframework.stereotype.Component

@Component
class UserMutation(private val userService: UserService) : Mutation {
    fun createUser(userPropertyType: UserPropertyType): UserType = userService.createUser(userPropertyType)
    fun updateUser(id: ID, userPropertyType: UserPropertyType): UserType = userService.updateUser(id, userPropertyType)
    fun deleteUser(id: ID): ID = userService.deleteUser(id)
}