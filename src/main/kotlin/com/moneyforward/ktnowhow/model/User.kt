package com.moneyforward.ktnowhow.model

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.repository.UserRepositoryImpl
import org.springframework.stereotype.Component

data class User(
    val id: ID,
    val name: String,
    val iconUrl: String?,
) {

    @GraphQLIgnore
    fun asInput(
        id: ID = this.id,
        name: String = this.name,
        iconUrl: String? = this.iconUrl
    ): UserInput = UserInput(id, name, iconUrl)
}

data class UserInput(
    val id: ID,
    val name: String,
    val iconUrl: String?,
)

@Component
class UserQuery(private val userRepository: UserRepositoryImpl) : Query {
    fun findUserById(id: ID): User = userRepository.findUser(id)
}

@Component
class UserMutation(private val userRepository: UserRepositoryImpl) : Mutation {
    fun createUser(name: String, iconUrl: String? = null): User = userRepository.createUser(name, iconUrl)
    fun updateUser(user: UserInput): User = userRepository.updateUser(user)
}
