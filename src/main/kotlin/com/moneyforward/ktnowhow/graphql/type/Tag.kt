package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.scalars.ID

data class Tag(
    override val rawId: Long,
    val name: String,
) : LongIdType

data class TagInput(
    override val id: ID,
    val name: String,
) : LongIdInputType
