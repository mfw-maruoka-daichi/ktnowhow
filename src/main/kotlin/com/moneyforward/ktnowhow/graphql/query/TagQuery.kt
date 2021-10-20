package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.Tag
import org.springframework.stereotype.Component

@Component
class TagQuery : Query {
    fun tags(): List<Tag> = TODO("Not yet implemented")
    fun findTagById(id: ID): Tag? = TODO("Not yet implemented")
}
