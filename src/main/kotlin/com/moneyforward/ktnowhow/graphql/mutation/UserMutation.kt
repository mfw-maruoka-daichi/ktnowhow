package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.UserInputType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.service.UserService
import org.springframework.stereotype.Component

@Component
class UserMutation(private val userService: UserService) : Mutation {
    @Deprecated("")
    fun createUser(name: String, iconUrl: String? = null): UserType = userService.createUser(name, iconUrl)
    fun updateUser(user: UserInputType): UserType = userService.updateUser(user)
}