package com.moneyforward.ktnowhow.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object H2TestDatabase : TestDatabase {

    override val url: String = "jdbc:h2:mem:ktnowhow;DB_CLOSE_DELAY=-1"
    override val driver: String = "org.h2.Driver"
    override val user: String = "sa"
    override val pass: String = ""

    override fun connect(): Database = Database.connect(datasource)

    private val datasource = HikariDataSource(
        HikariConfig().apply {
            jdbcUrl = url
            driverClassName = driver
            username = user
            password = pass
            isAutoCommit = false
        }
    )
}