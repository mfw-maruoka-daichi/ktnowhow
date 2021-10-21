package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.extention.LongIdTypeFeature
import com.moneyforward.ktnowhow.graphql.type.User
import com.moneyforward.ktnowhow.graphql.type.UserInput
import com.moneyforward.ktnowhow.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService, LongIdTypeFeature {
    override fun findUserBy(id: ID): User? {
        val rawId = id.getRawId(User::class) ?: throw IllegalArgumentException("todo validation error")
        return userRepository.findUserBy(rawId)
    }

    override fun createUser(name: String, iconUrl: String?): User {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserInput): User {
        TODO("Not yet implemented")
    }
}