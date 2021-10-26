package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extention.LongIdTypeFeature

@GraphQLIgnore
interface LongIdType : Type, LongIdTypeFeature {
    override val id: ID
        get() = rawId.toID(this::class)

    @GraphQLIgnore
    val rawId: Long
}
