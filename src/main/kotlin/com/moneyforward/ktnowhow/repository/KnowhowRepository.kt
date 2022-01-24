package com.moneyforward.ktnowhow.repository

import com.moneyforward.ktnowhow.model.Knowhow

interface KnowhowRepository {
    fun getAll(): List<Knowhow>
    fun addKnowhow(title: String, url: String, authorId: Long, tagIds: List<Long>): Knowhow
}