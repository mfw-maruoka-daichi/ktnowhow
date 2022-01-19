package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.getRawId
import com.moneyforward.ktnowhow.graphql.extension.id.toID
import com.moneyforward.ktnowhow.graphql.type.UserInputType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.graphql.type.validation.UserValidation
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import com.moneyforward.ktnowhow.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService, UserValidation {

    @Transactional
    override fun findUserById(id: ID): UserType? {
        val rawId = id.getRawId(UserType::class) ?: throw IllegalArgumentException("invalid ID")
        return userRepository.findUserBy(rawId)?.toUserType()
    }

    @Transactional
    override fun createUser(name: String, iconUrl: String?): UserType {
        validateUserProperty(name, iconUrl)
        return userRepository.createUser(name, iconUrl).toUserType()
    }

    @Transactional
    override fun updateUser(user: UserInputType): UserType {
        user.validate()
        return userRepository.updateUser(user.toUserInput())?.toUserType()
            ?: throw IllegalStateException("${user.id} not found")
    }

    @Transactional
    override fun deleteUser(id: ID): ID {
        val rawId = id.getRawId(UserType::class) ?: throw IllegalArgumentException("invalid ID")
        return userRepository.deleteUser(rawId)?.toID(UserType::class)
            ?: throw IllegalStateException("$id not found")
    }

    private fun UserInputType.toUserInput(): UserInput =
        UserInput(
            id = rawId ?: throw IllegalArgumentException("invalid ID"),
            name = name,
            iconUrl = iconUrl,
        )
}

fun User.toUserType(): UserType =
    UserType(
        rawId = id,
        name = name,
        iconUrl = iconUrl,
    )