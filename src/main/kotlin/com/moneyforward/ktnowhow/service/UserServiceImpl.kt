package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.getRawId
import com.moneyforward.ktnowhow.graphql.extension.id.toID
import com.moneyforward.ktnowhow.graphql.type.UserPropertyType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.graphql.type.validation.UserValidation
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.UndefinedUser
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.repository.UserRepository
import com.moneyforward.ktnowhow.service.annotation.Transactional
import graphql.relay.Connection
import graphql.relay.DefaultConnection
import graphql.relay.DefaultPageInfo
import graphql.relay.Edge
import graphql.relay.PageInfo
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, UserValidation {
    override fun users(first: Int, after: String): Connection<UserType> {
        val edges: List<Edge<UserType>> = emptyList()
        val pageInfo: PageInfo = DefaultPageInfo(null, null, false, false)
        // todo fetch data from repository and convert Connection
        return DefaultConnection(edges, pageInfo)
    }

    @Transactional
    override fun findUserById(id: ID): UserType? {
        val rawId = id.getRawId(UserType::class) ?: throw IllegalArgumentException("invalid ID")
        return userRepository.findUserBy(rawId)?.toUserType()
    }

    @Transactional
    override fun createUser(userProperty: UserPropertyType): UserType = upsertUser(userProperty.toUser())

    @Transactional
    override fun updateUser(id: ID, userProperty: UserPropertyType): UserType = upsertUser(userProperty.toUser(id))

    @Transactional
    override fun deleteUser(id: ID): ID {
        val rawId = id.getRawId(UserType::class) ?: throw IllegalArgumentException("invalid ID")
        return userRepository.deleteUser(rawId)?.toID(UserType::class)
            ?: throw IllegalStateException("$id not found")
    }

    private fun upsertUser(user: User): UserType {
        return userRepository.upsertUser(user).toUserType()
    }

    private fun UserPropertyType.toUser(id: ID? = null): User {
        validate()
        return id?.let {
            DefinedUser(
                rawId = it.getRawId(UserType::class) ?: throw IllegalArgumentException("invalid ID"),
                name = name,
                iconUrl = iconUrl
            )
        } ?: UndefinedUser(
            name = name,
            iconUrl = iconUrl
        )
    }
}

// todo どこか別のところで定義する
fun DefinedUser.toUserType(): UserType =
    UserType(
        rawId = rawId,
        UserPropertyType(name, iconUrl)
    )
