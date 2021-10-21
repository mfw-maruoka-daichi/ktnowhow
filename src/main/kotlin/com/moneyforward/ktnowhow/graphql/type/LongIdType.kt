package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID

@GraphQLIgnore
interface LongIdType : Type {
    companion object {
        fun getRawId(id: ID) = id.parse().last().toLongOrNull()
    }

    override val id: ID
        get() = ID("${this::class.simpleName}").combine(rawId)

    @GraphQLIgnore
    val rawId: Long
}

private const val delimiter = ":"
private fun ID.combine(rawId: Any): ID = ID("${this.value}$delimiter$rawId")
private fun ID.parse(): List<String> = this.value.split(delimiter)
