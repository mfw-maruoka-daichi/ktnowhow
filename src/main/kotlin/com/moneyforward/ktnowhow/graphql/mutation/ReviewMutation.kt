package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.ReviewInputType
import com.moneyforward.ktnowhow.graphql.type.ReviewType
import org.springframework.stereotype.Component

@Component
class ReviewMutation : Mutation {
    fun addReview(knowhowId: ID, rate: Int, comment: String? = null): ReviewType = TODO("Not yet implemented")
    fun updateReview(review: ReviewInputType): ReviewType = TODO("Not yet implemented")
}
