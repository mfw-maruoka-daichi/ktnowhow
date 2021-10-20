package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.extention.parse
import com.moneyforward.ktnowhow.graphql.type.Tag.Companion.getRawId

data class Tag(
    @GraphQLIgnore
    override val rawId: Long,
    val name: String,
) : Type {

    @GraphQLIgnore
    companion object {
        fun getRawId(id: ID): Long? = id.parse().toLongOrNull()
    }

    @GraphQLIgnore
    fun asInput(): TagInput = TagInput(id, name)
}

data class TagInput(
    override val id: ID,
    val name: String,
) : InputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = getRawId(id)
}
