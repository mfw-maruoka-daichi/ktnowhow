package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extention.id.toID

@GraphQLIgnore
interface LongIdType : Type {
    override val id: ID
        get() = requireNotNull(rawId.toID(this::class))

    @GraphQLIgnore
    val rawId: Long
}
