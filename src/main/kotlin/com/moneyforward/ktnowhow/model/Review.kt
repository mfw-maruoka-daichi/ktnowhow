package com.moneyforward.ktnowhow.model

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

data class Review(
    val id: ID,
    val knowhowID: ID,
    val rate: Int,
    val comment: String?,
    val author: User,
) {

    @GraphQLIgnore
    fun asInput(
        id: ID = this.id,
        rate: Int = this.rate,
        comment: String? = this.comment
    ): ReviewInput = ReviewInput(id, rate, comment)
}

data class ReviewInput(
    val id: ID,
    val rate: Int,
    val comment: String?,
)

@Component
class ReviewQuery : Query {
    fun findReviewById(id: ID): Review = TODO("Not yet implemented")
    fun findReviewByKnowhowId(knowhowId: ID): List<Review> = TODO("Not yet implemented")
    fun findReviewByRate(rate: Int): List<Review> = TODO("Not yet implemented")
}

@Component
class ReviewMutation : Mutation {
    fun addReview(knowhowID: ID, rate: Int, comment: String? = null): Review = TODO("Not yet implemented")
    fun updateReview(review: ReviewInput): Review = TODO("Not yet implemented")
}
