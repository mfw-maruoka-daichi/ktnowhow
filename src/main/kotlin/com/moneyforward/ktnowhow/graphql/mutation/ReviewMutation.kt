package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.Review
import com.moneyforward.ktnowhow.graphql.type.ReviewInput
import org.springframework.stereotype.Component

@Component
class ReviewMutation : Mutation {
    fun addReview(knowhowId: ID, rate: Int, comment: String? = null): Review = TODO("Not yet implemented")
    fun updateReview(review: ReviewInput): Review = TODO("Not yet implemented")
}
