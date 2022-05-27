package com.moneyforward.ktnowhow.graphql.relay

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.common.PaginationDirection
import com.moneyforward.ktnowhow.graphql.type.Type
import graphql.relay.*

class ConnectionImpl<T : Type>(
    first: Int?,
    after: String?,
    last: Int?,
    before: String?,
    fetcher: (cursor: ID?, pageSize: Int, direction: PaginationDirection) -> FetchResult<T>,
) : Connection<T> {

    private val _edges: List<Edge<T>>
    private val _pageInfo: PageInfo

    init {
        when {
            // first,lastの同時指定は不可 https://relay.dev/graphql/connections.htm#note-a97ec
            first != null && last != null -> throw IllegalArgumentException() // todo message
            // first/after(forward pagination), last/before(backward pagination)の組み合わせのみ許可
            first != null && before != null || last != null && after != null -> throw IllegalArgumentException() // todo message
            // fist,lastは負の数は許可しない
            first != null && first < 0 -> throw IllegalArgumentException("The page size must not be negative: first=$first")
            last != null && last < 0 -> throw IllegalArgumentException("The page size must not be negative: last=$last")
        }

        val (pageSize, direction) = first?.let { it to PaginationDirection.Forward }
            ?: last?.let { it to PaginationDirection.Backward }
            ?: throw IllegalArgumentException() // todo message
        val cursor = after?.let { ID(it) } ?: before?.let { ID(it) }
        val fetched = fetcher(cursor, pageSize, direction)

        _edges = fetched.nodes.map { DefaultEdge(it, DefaultConnectionCursor(it.id.value)) }
        _pageInfo = DefaultPageInfo(
            _edges.firstOrNull()?.cursor,
            _edges.lastOrNull()?.cursor,
            last?.let { fetched.hasMore } ?: false,
            first?.let { fetched.hasMore } ?: false
        )
    }

    override fun getEdges(): List<Edge<T>> = _edges
    override fun getPageInfo(): PageInfo = _pageInfo

    data class FetchResult<T>(
        val nodes: List<T>,
        val hasMore: Boolean
    )
}