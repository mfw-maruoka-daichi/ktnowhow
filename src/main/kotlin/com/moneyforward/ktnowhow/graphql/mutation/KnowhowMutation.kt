package com.moneyforward.ktnowhow.graphql.mutation

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.moneyforward.ktnowhow.graphql.type.KnowhowInputType
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import com.moneyforward.ktnowhow.service.KnowhowService
import org.springframework.stereotype.Component

@Component
class KnowhowMutation(private val knowhowService: KnowhowService) : Mutation {
    // todo authorIdはリクエストの認証情報から取得すべき(簡略化のためとりあえず)
    fun addKnowhow(title: String, url: String, authorId: ID, tagIds: List<ID>? = null): KnowhowType =
        knowhowService.addKnowhow(title, url, authorId, tagIds)

    fun updateKnowhow(knowhow: KnowhowInputType): KnowhowType = knowhowService.updateKnowhow(knowhow)
    fun deleteKnowhow(id: ID): ID = knowhowService.deleteKnowhow(id)
}