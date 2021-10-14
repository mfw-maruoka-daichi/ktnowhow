package com.moneyforward.ktnowhow.model

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.expediagroup.graphql.server.operations.Mutation
import com.expediagroup.graphql.server.operations.Query
import com.expediagroup.graphql.server.operations.Subscription
import org.reactivestreams.Publisher
import org.springframework.stereotype.Component

data class Knowhow(
    val id: ID,
    val title: String,
    val url: String,
    // todo CustomScalarは作るとして型は要検討 コード共有前提だとkotlinx.datetimeにしたい(ZonedDateTimeがないのでTimeZoneどうするか)
    // val publicationDate: ZonedDateTime,
    val author: User,
    val tags: List<Tag>?,
    val reviews: List<Review>,
) {

    @GraphQLIgnore
    fun asInput(
        id: ID = this.id,
        title: String = this.title,
        url: String = this.url,
        tags: List<Tag>? = this.tags
    ): KnowhowInput = KnowhowInput(id, title, url, tags)
}

data class KnowhowInput(
    val id: ID,
    val title: String,
    val url: String,
    val tags: List<Tag>?,
)

@Component
class KnowhowQuery : Query {
    fun knowhows(): List<Knowhow> = TODO("Not yet implemented")
    fun findKnowhowById(id: ID): Knowhow = TODO("Not yet implemented")
    fun findKnowhowByTitle(title: String): List<Knowhow> = TODO("Not yet implemented")
    fun findKnowhowByTags(tagIds: List<ID>): List<Knowhow> = TODO("Not yet implemented")
}

@Component
class KnowhowMutation : Mutation {
    fun addKnowhow(title: String, url: String, tags: List<Tag>? = null): Knowhow = TODO("Not yet implemented")
    fun updateKnowhow(knowhow: KnowhowInput): Knowhow = TODO("Not yet implemented")
}

@Component
class KnowhowSubscription : Subscription {
    fun knowhowChanges(id: ID): Publisher<Knowhow> = TODO("Not yet implemented")
}
