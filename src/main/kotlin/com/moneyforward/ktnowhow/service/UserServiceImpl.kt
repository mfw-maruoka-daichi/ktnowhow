package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extention.LongIdTypeFeature
import com.moneyforward.ktnowhow.graphql.type.UserInputType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import com.moneyforward.ktnowhow.repository.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService, LongIdTypeFeature {

    @Transactional
    override fun findUserBy(id: ID): UserType? {
        val rawId = id.getRawId(UserType::class) ?: throw IllegalArgumentException("todo validation error")
        return userRepository.findUserBy(rawId)?.toUserType()
    }

    @Transactional
    override fun createUser(name: String, iconUrl: String?): UserType {
        // todo validation
        return userRepository.createUser(name, iconUrl).toUserType()
    }

    override fun updateUser(user: UserInputType): UserType {
        TODO("Not yet implemented")
    }

    private fun User.toUserType(): UserType =
        UserType(
            rawId = id,
            name = name,
            iconUrl = iconUrl,
        )

    private fun UserInputType.toUserInput(): UserInput =
        UserInput(
            name = name,
            iconUrl = iconUrl,
        )
}