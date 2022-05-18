package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.db.entity.UserEntity
import com.moneyforward.ktnowhow.db.table.Users
import com.moneyforward.ktnowhow.model.DefinedUser
import com.moneyforward.ktnowhow.model.UndefinedUser
import com.moneyforward.ktnowhow.model.User
import com.moneyforward.ktnowhow.repository.common.SortOrder
import com.moneyforward.ktnowhow.repository.common.toDbSortOrder
import org.jetbrains.exposed.sql.select
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : UserRepository {
    override fun getAll(): List<DefinedUser> = UserEntity.all().map { it.toUser() }

    override fun fetch(cursor: Long, limit: Int, sortOrder: SortOrder): List<DefinedUser> {
        val query = when (sortOrder) {
            SortOrder.Asc -> Users.select { Users.id greater (cursor) }
            SortOrder.Desc -> Users.select { Users.id less (cursor) }
        }

        return query.orderBy(Users.id, sortOrder.toDbSortOrder()).limit(limit)
            .map {
                DefinedUser(
                    rawId = it[Users.id].value,
                    name = it[Users.name],
                    iconUrl = it[Users.iconUrl]
                )
            }
    }

    override fun findUserBy(id: Long): DefinedUser? =
        UserEntity.findById(id)?.toUser()

    override fun upsertUser(user: User): DefinedUser {
        return if (user.isIdDefined()) updateUser(user) else createUser(user)
    }

    private fun createUser(user: UndefinedUser): DefinedUser = UserEntity.new {
        this.name = user.name
        this.iconUrl = user.iconUrl
    }.toUser()

    private fun updateUser(user: DefinedUser): DefinedUser = UserEntity.findById(user.id.value)?.apply {
        user.name.let { name = it }
        user.iconUrl.let { iconUrl = it }
    }?.toUser() ?: throw IllegalStateException("user.id=${user.id} not found")

    override fun deleteUser(id: Long): Long? =
        UserEntity.findById(id)?.apply {
            delete()
        }?.id?.value
}

fun UserEntity.toUser(): DefinedUser = DefinedUser(
    rawId = id.value,
    name = name,
    iconUrl = iconUrl,
)
