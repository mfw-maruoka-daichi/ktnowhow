package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.model.Tag

interface TagRepository {
    fun getAll(): List<Tag>
    fun createTag(name: String): Tag
    fun createTags(names: List<String>): List<Tag>
}