package com.moneyforward.ktnowhow.repository.common

import org.jetbrains.exposed.sql.SortOrder as DbSortOrder

sealed class SortOrder {
    object Asc : SortOrder()
    object Desc : SortOrder()
}

fun SortOrder.toDbSortOrder(): DbSortOrder =
    when (this) {
        SortOrder.Asc -> DbSortOrder.ASC
        SortOrder.Desc -> DbSortOrder.DESC
    }
