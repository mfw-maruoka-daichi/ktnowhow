package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.extention.parse

data class User(
    @GraphQLIgnore
    override val rawId: Long,
    val name: String,
    val iconUrl: String?,
) : Type {

    @GraphQLIgnore
    companion object {
        fun getRawId(id: ID): Long? = id.parse().toLongOrNull()
    }

    @GraphQLIgnore
    fun asInput(): UserInput = UserInput(id, name, iconUrl)

}

data class UserInput(
    override val id: ID,
    val name: String,
    val iconUrl: String?,
) : InputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = User.getRawId(id)
}
