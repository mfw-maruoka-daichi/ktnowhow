package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extention.id.getRawId

@GraphQLName("Tag")
data class TagType(
    override val rawId: Long,
    val name: String,
) : LongIdType

@GraphQLName("TagInput")
data class TagInputType(
    override val id: ID,
    val name: String? = null,
) : LongIdInputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = id.getRawId(TagType::class)
}
