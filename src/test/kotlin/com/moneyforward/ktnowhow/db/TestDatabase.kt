package com.moneyforward.ktnowhow.db

import org.jetbrains.exposed.sql.Database

sealed interface TestDatabase {

    val url: String
    val driver: String
    val user: String
    val pass: String

    fun connect(): Database
}