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

    private val KClass<out LongIdType>.graphqlTypeName: String?
        get() = (annotations.find { it.annotationClass == GraphQLName::class } as? GraphQLName)?.value ?: simpleName

    fun Long.toID(clazz: KClass<out LongIdType>): ID? =
        clazz.graphqlTypeName?.let {
            ID("$it$idDelimiter$this")
        }

    fun ID.getRawId(clazz: KClass<out LongIdType>): Long? {
        val (prefix, rawId) = value.split(idDelimiter).let {
            if (it.size != 2) return null
            it.first() to it.last()
        }

        return if (prefix == clazz.graphqlTypeName) rawId.toLongOrNull() else null
    }
}
