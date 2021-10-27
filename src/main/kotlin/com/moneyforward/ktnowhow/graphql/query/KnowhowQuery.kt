package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import org.springframework.stereotype.Component

@Component
class KnowhowQuery : Query {
    fun knowhows(): List<KnowhowType> = TODO("Not yet implemented")
    fun findKnowhowById(id: ID): KnowhowType? = TODO("Not yet implemented")
    fun findKnowhowByTitle(title: String): List<KnowhowType> = TODO("Not yet implemented")
    fun findKnowhowByTags(tagIds: List<ID>): List<KnowhowType> = TODO("Not yet implemented")
}