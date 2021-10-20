package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.extention.parse
import com.moneyforward.ktnowhow.graphql.type.Review.Companion.getRawId

data class Review(
    @GraphQLIgnore
    override val rawId: Long,
    val knowhowId: ID,
    val rate: Int,
    val comment: String?,
    val author: User,
) : Type {

    @GraphQLIgnore
    companion object {
        fun getRawId(id: ID): Long? = id.parse().toLongOrNull()
    }

    @GraphQLIgnore
    fun asInput(): ReviewInput = ReviewInput(id, rate, comment)
}

data class ReviewInput(
    override val id: ID,
    val rate: Int,
    val comment: String?,
) : InputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = getRawId(id)
}
