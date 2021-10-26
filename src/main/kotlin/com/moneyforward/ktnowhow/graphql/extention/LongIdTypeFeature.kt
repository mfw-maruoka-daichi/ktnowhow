package com.moneyforward.ktnowhow.graphql.extention

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.LongIdType
import kotlin.reflect.KClass

@GraphQLIgnore
interface LongIdTypeFeature {
    private val idDelimiter: String
        get() = ":"

    fun Long.toID(clazz: KClass<out LongIdType>): ID = ID("${clazz.simpleName}$idDelimiter$this")

    fun ID.getRawId(clazz: KClass<out LongIdType>): Long? {
        val (prefix, rawId) = value.split(idDelimiter).let {
            if (it.size != 2) return null
            it.first() to it.last()
        }

        val graphqlTypeName =
            (clazz.annotations.find { it.annotationClass == GraphQLName::class } as? GraphQLName)?.value
                ?: clazz.simpleName

        return if (prefix != graphqlTypeName) null else rawId.toLongOrNull()
    }
}
