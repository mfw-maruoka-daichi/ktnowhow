package com.moneyforward.ktnowhow.model

data class User(
    val id: Long,
    val name: String,
    val iconUrl: String?,
)

data class UserInput(
    val id: Long,
    val name: String?,
    val iconUrl: String?,
)
