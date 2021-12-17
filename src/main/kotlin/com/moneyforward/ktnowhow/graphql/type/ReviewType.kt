package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.getRawId

@GraphQLName("Review")
data class ReviewType(
    override val rawId: Long,
    val knowhowId: ID,
    val rate: Int,
    val comment: String?,
    val author: UserType,
) : LongIdType

@GraphQLName("ReviewInput")
data class ReviewInputType(
    override val id: ID,
    val rate: Int? = null,
    val comment: String? = null,
) : LongIdInputType {

    @GraphQLIgnore
    override val rawId: Long? = id.getRawId(ReviewType::class)
}
