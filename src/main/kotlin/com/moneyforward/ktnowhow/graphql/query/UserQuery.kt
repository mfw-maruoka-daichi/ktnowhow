package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.UserConnection
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.service.UserService
import org.springframework.stereotype.Component

@Component
class UserQuery(private val userService: UserService) : Query {
    fun users(
        first: Int? = null,
        after: String? = null,
        last: Int? = null,
        before: String? = null
    ): UserConnection = userService.users(first, after, last, before)

    fun findUserById(id: ID): UserType? = userService.findUserById(id)
}
