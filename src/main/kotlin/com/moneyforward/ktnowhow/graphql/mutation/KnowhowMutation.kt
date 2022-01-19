package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.KnowhowInputType
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import org.springframework.stereotype.Component

@Component
class KnowhowMutation : Mutation {
    fun addKnowhow(title: String, url: String, tagIds: List<ID>? = null): KnowhowType = TODO("Not yet implemented")
    fun updateKnowhow(knowhow: KnowhowInputType): KnowhowType = TODO("Not yet implemented")
    fun deleteKnowhow(knowhowId: ID): ID = TODO("Not yet implemented")
}