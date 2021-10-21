package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.scalars.ID

data class Review(
    override val rawId: Long,
    val knowhowId: ID,
    val rate: Int,
    val comment: String?,
    val author: User,
) : LongIdType

data class ReviewInput(
    override val id: ID,
    val rate: Int,
    val comment: String?,
) : LongIdInputType
