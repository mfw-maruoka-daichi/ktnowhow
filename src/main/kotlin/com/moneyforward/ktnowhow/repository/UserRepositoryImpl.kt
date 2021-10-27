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
//        return Users.select { Users.id eq id }.singleOrNull()?.toUser()

        return UserEntity.findById(id)?.toUser()
    }

    override fun createUser(name: String, iconUrl: String?): User {
//        val newUserByDsl = Users.insert {
//            it[Users.name] = name
//            it[Users.iconUrl] = iconUrl
//        }.resultedValues?.singleOrNull()?.toUser()
//        requireNotNull(newUserByDsl){"なんでinsertの結果取得でnullチェックいるん？"}
//
//        return newUserByDsl.toUser()

        return UserEntity.new {
            this.name = name
            this.iconUrl = iconUrl
        }.toUser()
    }

    override fun updateUser(user: UserInput): User? {
//        // update対象行数を返す
//        Users.update({ Users.id eq user.id }) {
//            // itでカラム指定しなければならないので、letでitが使えない
//            // with(user){}としたいが、tableと変数名が被るのでuser.fooとしたほうがタイプ数少ない
//            user.name?.let { newName -> it[name] = newName }
//            user.iconUrl?.let { newIconUrl -> it[iconUrl] = newIconUrl }
//        }
//        return findUserBy(user.id)

        return UserEntity.findById(user.id)?.apply {
            user.name?.let { name = it }
            user.iconUrl?.let { iconUrl = it }
        }?.toUser()
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