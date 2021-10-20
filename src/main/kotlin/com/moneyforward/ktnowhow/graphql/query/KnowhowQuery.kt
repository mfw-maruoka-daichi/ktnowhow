package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.Knowhow
import org.springframework.stereotype.Component

@Component
class KnowhowQuery : Query {
    fun knowhows(): List<Knowhow> = TODO("Not yet implemented")
    fun findKnowhowById(id: ID): Knowhow? = TODO("Not yet implemented")
    fun findKnowhowByTitle(title: String): List<Knowhow> = TODO("Not yet implemented")
    fun findKnowhowByTags(tagIds: List<ID>): List<Knowhow> = TODO("Not yet implemented")
}