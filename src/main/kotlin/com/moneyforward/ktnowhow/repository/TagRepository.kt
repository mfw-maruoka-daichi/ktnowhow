package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.model.Tag

interface TagRepository {
    fun getAll(): List<Tag>
}