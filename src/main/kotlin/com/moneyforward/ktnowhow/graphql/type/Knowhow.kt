package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID

data class Knowhow(
    override val rawId: Long,
    val title: String,
    val url: String,
    // todo CustomScalarは作るとして型は要検討 コード共有前提だとkotlinx.datetimeにしたい(ZonedDateTimeがないのでTimeZoneどうするか)
    // val publicationDate: ZonedDateTime,
    val author: User,
    val tags: List<Tag>?,
    val reviews: List<Review>,
) : LongIdType

data class KnowhowInput(
    override val id: ID,
    val title: String,
    val url: String,
    val tagIds: List<ID>?,
) : LongIdInputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = id.getRawId(Knowhow::class)
}
