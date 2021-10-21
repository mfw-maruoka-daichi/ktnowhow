package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore

@GraphQLIgnore
interface LongIdInputType : Type {

    @GraphQLIgnore
    val rawId: Long?
        get() = LongIdType.getRawId(id)
}