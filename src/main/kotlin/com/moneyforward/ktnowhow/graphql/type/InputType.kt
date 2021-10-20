package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID

@GraphQLIgnore
interface InputType {
    val id: ID

    // overrideで型変更すると@GraphQLIgnoreが機能せずビルドエラーになるのでここでは@GraphQLIgnoreしない
    val rawId: Any?
}