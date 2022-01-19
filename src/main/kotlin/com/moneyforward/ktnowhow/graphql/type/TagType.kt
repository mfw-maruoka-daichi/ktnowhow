package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLName

@GraphQLName("Tag")
data class TagType(
    override val rawId: Long,
    val name: String,
) : LongIdType
