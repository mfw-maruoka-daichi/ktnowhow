package com.moneyforward.ktnowhow.graphql.type

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.extention.parse
import com.moneyforward.ktnowhow.graphql.type.Knowhow.Companion.getRawId

data class Knowhow(
    @GraphQLIgnore
    override val rawId: Long,
    val title: String,
    val url: String,
    // todo CustomScalarは作るとして型は要検討 コード共有前提だとkotlinx.datetimeにしたい(ZonedDateTimeがないのでTimeZoneどうするか)
    // val publicationDate: ZonedDateTime,
    val author: User,
    val tags: List<Tag>?,
    val reviews: List<Review>,
) : Type {

    @GraphQLIgnore
    companion object {
        fun getRawId(id: ID): Long? = id.parse().toLongOrNull()
    }

    @GraphQLIgnore
    fun asInput(): KnowhowInput = KnowhowInput(id, title, url, tags)
}

data class KnowhowInput(
    override val id: ID,
    val title: String,
    val url: String,
    val tags: List<Tag>?,
) : InputType {

    @GraphQLIgnore
    override val rawId: Long?
        get() = getRawId(id)
}
