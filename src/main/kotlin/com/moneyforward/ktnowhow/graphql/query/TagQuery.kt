package com.moneyforward.ktnowhow.graphql.query

import com.expediagroup.graphql.server.operations.Query
import com.moneyforward.ktnowhow.graphql.type.TagType
import com.moneyforward.ktnowhow.service.TagService
import org.springframework.stereotype.Component

@Component
class TagQuery(private val tagService: TagService) : Query {
    fun tags(): List<TagType> = tagService.getAllTags()
}
