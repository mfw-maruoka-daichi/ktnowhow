package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.TagType
import org.springframework.stereotype.Component

@Component
class TagQuery : Query {
    fun tags(): List<TagType> = TODO("Not yet implemented")
}
