package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.toID

@GraphQLIgnore
interface LongIdType : Type {
    override val id: ID
        get() = rawId.toID(this::class)

    @GraphQLIgnore
    val rawId: Long
}
