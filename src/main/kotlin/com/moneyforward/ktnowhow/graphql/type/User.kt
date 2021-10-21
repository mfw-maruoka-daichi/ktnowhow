package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.scalars.ID

data class User(
    override val rawId: Long,
    val name: String,
    val iconUrl: String?,
) : LongIdType

data class UserInput(
    override val id: ID,
    val name: String,
    val iconUrl: String?,
) : LongIdInputType
