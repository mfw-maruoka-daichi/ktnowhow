package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.db.table.Users
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import org.jetbrains.exposed.sql.ResultRow
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findUserBy(id: Long): User? {
//        val userByDsl = Users.select { Users.id eq id }.singleOrNull()?.toUser()
//        println("Find user: $userByDsl")

        val user = UserEntity.findById(id)?.toUser()
        println("Find user: $user")

        return user
    }

    override fun createUser(name: String, iconUrl: String?): User {
//        val newUserByDsl = Users.insert {
//            it[Users.name] = name
//            it[Users.iconUrl] = iconUrl
//        }.resultedValues?.singleOrNull()?.toUser()
//        println("Create user: $newUserByDsl")
//        requireNotNull(newUserByDsl){"なんでinsertの結果取得でnullチェックいるん？"}
//
//        return newUserByDsl.toUser()

        val newUser = UserEntity.new {
            this.name = name
            this.iconUrl = iconUrl
        }
        println("Create user: $newUser")

        return newUser.toUser()
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