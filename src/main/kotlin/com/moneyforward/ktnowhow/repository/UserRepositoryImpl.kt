package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.model.UserInput
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findUserBy(id: Long): User? =
        UserEntity.findById(id)?.toUser()

    override fun createUser(name: String, iconUrl: String?): User =
        UserEntity.new {
            this.name = name
            this.iconUrl = iconUrl
        }.toUser()

    override fun updateUser(user: UserInput): User? =
        UserEntity.findById(user.id)?.apply {
            user.name?.let { name = it }
            user.iconUrl?.let { iconUrl = it }
        }?.toUser()

    override fun deleteUser(id: Long): Long? =
        UserEntity.findById(id)?.apply {
            delete()
        }?.id?.value
}

fun UserEntity.toUser(): User = User(
    id = id.value,
    name = name,
    iconUrl = iconUrl,
)
