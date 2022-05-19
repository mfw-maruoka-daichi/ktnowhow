package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.getRawId
import com.moneyforward.ktnowhow.graphql.extension.id.toID
import com.moneyforward.ktnowhow.graphql.relay.Connection
import com.moneyforward.ktnowhow.graphql.relay.PaginationDirection
import com.moneyforward.ktnowhow.graphql.type.*
import com.moneyforward.ktnowhow.graphql.type.validation.UserValidation
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.UndefinedUser
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.repository.UserRepository
import com.moneyforward.ktnowhow.repository.common.SortOrder
import com.moneyforward.ktnowhow.service.annotation.Transactional
import org.springframework.stereotype.Service
import graphql.relay.Connection as RelayConnection

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, UserValidation {

    @Transactional
    override fun users(first: Int?, after: String?, last: Int?, before: String?): UserConnection {
        fun fetch(cursor: ID?, limit: Int, direction: PaginationDirection): List<DefinedUser> {
            val (sortOrder, fallbackRawId) = when (direction) {
                PaginationDirection.Forward -> SortOrder.Asc to 0L
                PaginationDirection.Backward -> SortOrder.Desc to Long.MAX_VALUE
            }
            val rawId = cursor?.getRawId(UserType::class) ?: fallbackRawId

            return userRepository.fetch(rawId, limit, sortOrder)
        }

        fun convert(user: DefinedUser): UserType {
            return user.toUserType()
        }

        return Connection(first, after, last, before, ::fetch, ::convert).toConnectionType()
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

    // todo UserConnection(Connection<UserType>)でよさそう
    private fun RelayConnection<UserType>.toConnectionType(): UserConnection =
        UserConnection(
            edges.map { UserEdge(it.node, it.cursor.value) },
            pageInfo.let { PageInfoType(it.isHasPreviousPage, it.isHasNextPage) }
        )
}

// todo どこか別のところで定義する
fun DefinedUser.toUserType(): UserType =
    UserType(
        rawId = rawId,
        UserPropertyType(name, iconUrl)
    )
