package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import com.moneyforward.ktnowhow.service.KnowhowService
import org.springframework.stereotype.Component

@Component
class KnowhowQuery(private val knowhowService: KnowhowService) : Query {
    fun knowhows(): List<KnowhowType> = knowhowService.getAllKnowhow()
    fun findKnowhowById(id: ID): KnowhowType? = knowhowService.findKnowhowById(id)
    fun findKnowhowByTitle(title: String): List<KnowhowType> = knowhowService.findKnowhowByTitle(title)
    fun findKnowhowByTags(tagIds: List<ID>): List<KnowhowType> = knowhowService.findKnowhowByTags(tagIds)
    fun findKnowhowByAuthor(authorID: ID): List<KnowhowType> = knowhowService.findKnowhowByAuthor(authorID)
}