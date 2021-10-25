package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID

data class Tag(
    override val rawId: Long,
    val name: String,
) : LongIdType

data class TagInput(
    override val id: ID,
    val name: String? = null,
) : LongIdInputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = id.getRawId(Tag::class)
}
