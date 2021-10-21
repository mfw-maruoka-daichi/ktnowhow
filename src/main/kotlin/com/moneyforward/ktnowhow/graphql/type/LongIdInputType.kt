package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.moneyforward.ktnowhow.extention.LongIdTypeFeature

@GraphQLIgnore
interface LongIdInputType : Type, LongIdTypeFeature {

    @GraphQLIgnore
    val rawId: Long?
}
