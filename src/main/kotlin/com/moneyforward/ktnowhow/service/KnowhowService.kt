package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.KnowhowInputType
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import org.reactivestreams.Publisher

interface KnowhowService {
    fun getAllKnowhow(): List<KnowhowType>
    fun findKnowhowById(id: ID): KnowhowType?
    fun findKnowhowByTitle(title: String): List<KnowhowType>
    fun findKnowhowByTags(tagIds: List<ID>): List<KnowhowType>
    fun findKnowhowByAuthor(authorID: ID): List<KnowhowType>
    fun addKnowhow(title: String, url: String, authorId: ID, tagIds: List<ID>? = null): KnowhowType
    fun updateKnowhow(knowhow: KnowhowInputType): KnowhowType
    fun deleteKnowhow(id: ID): ID
    fun providePublisher(id: ID): Publisher<KnowhowType>
}