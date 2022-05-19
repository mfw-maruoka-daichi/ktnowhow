package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLName

@GraphQLName("PageInfo")
data class PageInfoType(
    val hasPreviousPage: Boolean,
    val hasNextPage: Boolean
)
