package com.moneyforward.ktnowhow.common

sealed class PaginationDirection {
    object Forward : PaginationDirection()
    object Backward : PaginationDirection()
}
