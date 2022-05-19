package com.moneyforward.ktnowhow.graphql.relay

import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.Type
import graphql.relay.*
import graphql.relay.Connection as RelayConnection

class Connection<T : Type, E>(
    private val first: Int?,
    private val after: String?,
    private val last: Int?,
    private val before: String?,
    private val fetcher: (cursor: ID?, limit: Int, direction: PaginationDirection) -> List<E>,
    private val converter: (data: E) -> T
) : RelayConnection<T> {

    private val pageSize: Int
    private val direction: PaginationDirection
    private var hasMorePages: Boolean = false

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

        this.pageSize = pageSize
        this.direction = direction
    }

    override fun getEdges(): List<Edge<T>> {
        val cursor = after?.let { ID(it) } ?: before?.let { ID(it) }

        val edges = fetcher(cursor, pageSize + 1, direction).mapIndexedNotNull { index, data ->
            if (index < pageSize) {
                converter(data).let { DefaultEdge(it, DefaultConnectionCursor(it.id.value)) }
            } else {
                hasMorePages = true
                null
            }
        }.let { if (direction == PaginationDirection.Backward) it.reversed() else it }

        return edges
    }

    override fun getPageInfo(): PageInfo = DefaultPageInfo(
        edges.firstOrNull()?.cursor,
        edges.lastOrNull()?.cursor,
        last?.let { hasMorePages } ?: false,
        first?.let { hasMorePages } ?: false
    )
}