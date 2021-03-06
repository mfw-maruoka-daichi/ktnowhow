package com.moneyforward.ktnowhow.service

import com.moneyforward.ktnowhow.graphql.type.TagType
import com.moneyforward.ktnowhow.model.Tag
import com.moneyforward.ktnowhow.repository.TagRepository
import com.moneyforward.ktnowhow.service.annotation.Transactional
import org.springframework.stereotype.Service

@Service
class TagServiceImpl(
    private val tagRepository: TagRepository
) : TagService {

    @Transactional
    override fun getAllTags(): List<TagType> = tagRepository.getAll().map { it.toTagType() }
}

fun Tag.toTagType(): TagType =
    TagType(
        rawId = id,
        name = name
    )
