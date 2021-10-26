package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID

@GraphQLName("User")
data class UserType(
    override val rawId: Long,
    val name: String,
    val iconUrl: String?,
) : LongIdType

@GraphQLName("UserInput")
data class UserInputType(
    override val id: ID,
    val name: String? = null,
    val iconUrl: String? = null,
) : LongIdInputType {

    @GraphQLIgnore
    override val rawId: Long? = id.getRawId(UserType::class)
}
