package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.extention.combine

@GraphQLIgnore
interface Type {
    val id: ID
        get() = ID("${this::class.simpleName}").combine(rawId)

    // overrideで型変更すると@GraphQLIgnoreが機能せずビルドエラーになるのでここでは@GraphQLIgnoreしない
    val rawId: Any
}
