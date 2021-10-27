package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.service.UserService
import org.springframework.stereotype.Component

@Component
class UserQuery(private val userService: UserService) : Query {
    fun findUserById(id: ID): UserType? = userService.findUserBy(id)
}
