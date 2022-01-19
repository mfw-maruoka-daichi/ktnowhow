package com.moneyforward.ktnowhow.service

import com.moneyforward.ktnowhow.graphql.type.TagType

interface TagService {
    fun getAllTags(): List<TagType>
}