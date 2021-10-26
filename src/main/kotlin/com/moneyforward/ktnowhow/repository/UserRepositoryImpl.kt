package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.db.table.Users
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findUserBy(id: Long): User? {
        val userByDsl = Users.select { Users.id eq id }.singleOrNull()?.toUser()
        println("DSL User:$userByDsl")

        val userByDao = UserEntity.findById(id)?.toUser()
        println("DAO User:$userByDao")

        return userByDao
    }

    override fun createUser(name: String, iconUrl: String?): User {
        TODO("Not yet implemented")
    }

    override fun updateUser(user: UserInput): User {
        TODO("Not yet implemented")
    }

    private fun ResultRow.toUser(): User = User(
        id = this[Users.id].value,
        name = this[Users.name],
        iconUrl = this[Users.iconUrl],
    )

    private fun UserEntity.toUser(): User = User(
        id = id.value,
        name = name,
        iconUrl = iconUrl,
    )
}