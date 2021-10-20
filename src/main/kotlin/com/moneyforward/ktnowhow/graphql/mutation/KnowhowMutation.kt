package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.Knowhow
import com.moneyforward.ktnowhow.graphql.type.KnowhowInput
import com.moneyforward.ktnowhow.graphql.type.Tag
import org.springframework.stereotype.Component

@Component
class KnowhowMutation : Mutation {
    fun addKnowhow(title: String, url: String, tags: List<Tag>? = null): Knowhow = TODO("Not yet implemented")
    fun updateKnowhow(knowhow: KnowhowInput): Knowhow = TODO("Not yet implemented")
}