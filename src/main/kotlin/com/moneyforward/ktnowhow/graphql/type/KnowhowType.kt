package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID

@GraphQLName("Knowhow")
data class KnowhowType(
    override val rawId: Long,
    val title: String,
    val url: String,
    // todo CustomScalarは作るとして型は要検討 コード共有前提だとkotlinx.datetimeにしたい(ZonedDateTimeがないのでTimeZoneどうするか)
    // val publicationDate: ZonedDateTime,
    val author: UserType,
    val tags: List<TagType>,
    val reviews: List<ReviewType>,
) : LongIdType

@GraphQLName("KnowhowInput")
data class KnowhowInputType(
    override val id: ID,
    val title: String? = null,
    val url: String? = null,
    val tagIds: List<ID>? = null,
) : LongIdInputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = id.getRawId(KnowhowType::class)
}
