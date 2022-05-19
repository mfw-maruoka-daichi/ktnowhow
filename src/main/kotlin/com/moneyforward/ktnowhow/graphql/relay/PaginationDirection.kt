package com.moneyforward.ktnowhow.graphql.relay

sealed class PaginationDirection {
    object Forward : PaginationDirection()
    object Backward : PaginationDirection()
}
