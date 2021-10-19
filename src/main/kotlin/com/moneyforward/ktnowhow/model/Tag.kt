package com.moneyforward.ktnowhow.model

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

data class Tag(
    val id: ID,
    val name: String,
) {

    @GraphQLIgnore
    fun asInput(
        id: ID = this.id,
        name: String = this.name
    ): TagInput = TagInput(id, name)
}

data class TagInput(
    val id: ID,
    val name: String,
)

@Component
class TagQuery : Query {
    fun tags(): List<Tag> = TODO("Not yet implemented")
    fun findTagById(id: ID): Tag = TODO("Not yet implemented")
}
