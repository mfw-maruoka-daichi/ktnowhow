package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.User
import com.moneyforward.ktnowhow.graphql.type.UserInput
import com.moneyforward.ktnowhow.service.UserService
import org.springframework.stereotype.Component

@Component
class UserMutation(private val userService: UserService) : Mutation {
    @Deprecated("")
    fun createUser(name: String, iconUrl: String? = null): User = userService.createUser(name, iconUrl)
    fun updateUser(user: UserInput): User = userService.updateUser(user)
}