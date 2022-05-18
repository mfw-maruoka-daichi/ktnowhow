package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.getRawId
import com.moneyforward.ktnowhow.graphql.extension.id.toID
import com.moneyforward.ktnowhow.graphql.type.*
import com.moneyforward.ktnowhow.graphql.type.validation.UserValidation
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.UndefinedUser
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.repository.UserRepository
import com.moneyforward.ktnowhow.repository.common.SortOrder
import com.moneyforward.ktnowhow.service.annotation.Transactional
import graphql.relay.*
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, UserValidation {

    @Transactional
    override fun users(first: Int?, after: String?, last: Int?, before: String?): UserConnection {

        // todo validationで
        when {
            // first,lastの同時指定は不可 https://relay.dev/graphql/connections.htm#note-a97ec
            first != null && last != null -> throw IllegalArgumentException()
            // first/after(forward pagination), last/before(backward pagination)の組み合わせのみ許可
            first != null && before != null || last != null && after != null -> throw IllegalArgumentException()
            // fist,lastは負の数は許可しない
            first != null && first < 0 || last != null && last < 0 -> throw IllegalArgumentException()
        }

        // first,lastどちらかは必須
        val (limit, sortOnFetch) = first?.let { it to SortOrder.Asc }
            ?: last?.let { it to SortOrder.Desc }
            ?: throw IllegalArgumentException()

        val rawId = after?.let { ID(it).getRawId(UserType::class) }
            ?: before?.let { ID(it).getRawId(UserType::class) }
            ?: let { if (sortOnFetch == SortOrder.Asc) 0L else Long.MAX_VALUE }

        val fetchedData: List<DefinedUser> = userRepository.fetch(rawId, limit + 1, sortOnFetch)

        var hasPages = false
        val edges: List<Edge<UserType>> = fetchedData.mapIndexedNotNull { index, definedUser ->
            if (index < limit) {
                val type = definedUser.toUserType()
                DefaultEdge(type, DefaultConnectionCursor(type.id.value))
            } else {
                hasPages = true
                null
            }
        }.let { if (sortOnFetch == SortOrder.Desc) it.reversed() else it }

        // first/afterでのhasPreviousPage, last/beforeでのhasNextPageは常にfalseにする
        // 効率的に判断できる場合のみでいい
        // https://relay.dev/graphql/connections.htm#sec-undefined.PageInfo.Fields
        val pageInfo: PageInfo = DefaultPageInfo(
            edges.firstOrNull()?.cursor,
            edges.lastOrNull()?.cursor,
            last?.let { hasPages } ?: false,
            first?.let { hasPages } ?: false
        )

        return DefaultConnection(edges, pageInfo).toConnection()
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
    private fun Connection<UserType>.toConnection(): UserConnection =
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
