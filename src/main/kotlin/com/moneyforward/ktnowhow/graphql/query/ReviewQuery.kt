package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.Review
import org.springframework.stereotype.Component

@Component
class ReviewQuery : Query {
    fun findReviewById(id: ID): Review? = TODO("Not yet implemented")
    fun findReviewByKnowhowId(knowhowId: ID): List<Review> = TODO("Not yet implemented")
    fun findReviewByRate(rate: Int): List<Review> = TODO("Not yet implemented")
}
