package com.moneyforward.ktnowhow.repository

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import java.util.*
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findUser(id: ID): User {
        // todo impl とりあえず固定値を返す
        return User(id = ID(UUID.randomUUID().toString()), name = "TestUser1", iconUrl = null)
    }

    override fun createUser(name: String, iconUrl: String?): User {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserInput): User {
        TODO("Not yet implemented")
    }
}