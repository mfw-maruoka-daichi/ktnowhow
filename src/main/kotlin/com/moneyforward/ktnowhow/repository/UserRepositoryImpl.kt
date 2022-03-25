package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.model.DefinedID
import com.moneyforward.ktnowhow.model.ID
import com.moneyforward.ktnowhow.model.UndefinedID
import com.moneyforward.ktnowhow.model.User
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun findUserBy(id: Long): User<DefinedID<Long>>? =
        UserEntity.findById(id)?.toUser()

    override fun upsertUser(user: User<ID>): User<DefinedID<Long>> {
        return if (user.isIDDefined()) updateUser(user)
        else createUser(user)
    }

    private fun createUser(user: User<UndefinedID>): User<DefinedID<Long>> = UserEntity.new {
        this.name = user.name
        this.iconUrl = user.iconUrl
    }.toUser()

    private fun updateUser(user: User<DefinedID<Long>>): User<DefinedID<Long>> =
        UserEntity.findById(user.id.value)?.apply {
            user.name.let { name = it }
            user.iconUrl.let { iconUrl = it }
        }?.toUser() ?: throw IllegalStateException("${user.id} not found")

    override fun deleteUser(id: Long): Long? =
        UserEntity.findById(id)?.apply {
            delete()
        }?.id?.value
}

fun UserEntity.toUser(): User<DefinedID<Long>> = User(
    id = DefinedID(id.value),
    name = name,
    iconUrl = iconUrl,
)
