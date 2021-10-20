package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.graphql.type.User
import com.moneyforward.ktnowhow.graphql.type.UserInput
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findUserBy(id: Long): User? {
        // todo impl とりあえず固定値を返す
        return User(rawId = id, name = "TestUser$id", iconUrl = null)
    }

    override fun createUser(name: String, iconUrl: String?): User {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserInput): User {
        TODO("Not yet implemented")
    }
}