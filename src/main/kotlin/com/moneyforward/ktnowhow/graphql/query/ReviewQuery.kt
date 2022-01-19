package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.ReviewType
import org.springframework.stereotype.Component

@Component
class ReviewQuery : Query {
    fun findReviewById(id: ID): ReviewType? = TODO("Not yet implemented")
    fun findReviewByKnowhowId(knowhowId: ID): List<ReviewType> = TODO("Not yet implemented")

    // todo lower or upper (実需要的にはupperだけでいい)
    fun findReviewByRate(rate: Int): List<ReviewType> = TODO("Not yet implemented")
}
