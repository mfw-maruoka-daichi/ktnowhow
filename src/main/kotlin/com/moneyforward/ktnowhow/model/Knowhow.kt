package com.moneyforward.ktnowhow.model

data class Knowhow(
    val id: Long,
    val title: String,
    val url: String,
    val author: User,
    val tags: List<Tag>
)
