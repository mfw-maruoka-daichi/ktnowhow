package com.moneyforward.ktnowhow.service

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.extension.id.getRawId
import com.moneyforward.ktnowhow.graphql.type.KnowhowInputType
import com.moneyforward.ktnowhow.graphql.type.KnowhowType
import com.moneyforward.ktnowhow.graphql.type.TagType
import com.moneyforward.ktnowhow.graphql.type.UserType
import com.moneyforward.ktnowhow.model.Knowhow
import com.moneyforward.ktnowhow.repository.KnowhowRepository
import org.reactivestreams.Publisher
import org.springframework.stereotype.Service

@Service
class KnowhowServiceImpl(
    private val knowhowRepository: KnowhowRepository
) : KnowhowService {

    override fun getAllKnowhow(): List<KnowhowType> =
        knowhowRepository.getAll().map { it.toKnowhowType() }

    override fun findKnowhowById(id: ID): KnowhowType? {
        TODO("Not yet implemented")
    }

    override fun findKnowhowByTitle(title: String): List<KnowhowType> {
        TODO("Not yet implemented")
    }

    override fun findKnowhowByTags(tagIds: List<ID>): List<KnowhowType> {
        TODO("Not yet implemented")
    }

    override fun findKnowhowByAuthor(authorID: ID): List<KnowhowType> {
        TODO("Not yet implemented")
    }

    override fun addKnowhow(title: String, url: String, authorId: ID, tagIds: List<ID>?): KnowhowType {
        val authorRawId = authorId.getRawId(UserType::class)
            ?: throw IllegalArgumentException("invalid author ID: $authorId")

        val tagRawIds = tagIds?.map {
            it.getRawId(TagType::class) ?: throw IllegalArgumentException("invalid tag ID: $it")
        } ?: emptyList()

        return knowhowRepository.addKnowhow(title, url, authorRawId, tagRawIds).toKnowhowType()
    }

    override fun updateKnowhow(knowhow: KnowhowInputType): KnowhowType {
        TODO("Not yet implemented")
    }

    override fun deleteKnowhow(id: ID): ID {
        TODO("Not yet implemented")
    }

    override fun providePublisher(id: ID): Publisher<KnowhowType> {
        TODO("Not yet implemented")
    }
}

fun Knowhow.toKnowhowType(): KnowhowType =
    KnowhowType(
        rawId = id,
        title = title,
        url = url,
        author = author.toUserType(),
        reviews = emptyList(), // todo reviews.map { it.toReviewType }
        tags = tags.map { it.toTagType() }
    )
