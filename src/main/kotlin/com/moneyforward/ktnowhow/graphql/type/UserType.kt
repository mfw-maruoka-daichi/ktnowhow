package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLName

@GraphQLName("User")
data class UserType(
    override val rawId: Long,
    val property: UserPropertyType
) : LongIdType

@GraphQLName("UserProperty")
data class UserPropertyType(
    val name: String,
    val iconUrl: String?,
)