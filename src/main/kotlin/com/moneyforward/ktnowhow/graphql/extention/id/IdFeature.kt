package com.moneyforward.ktnowhow.graphql.extention.id

import com.expediagroup.graphql.generator.annotations.GraphQLName
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.LongIdType
import graphql.relay.Relay
import kotlin.reflect.KClass

private val KClass<out LongIdType>.graphqlTypeName: String?
    get() = (annotations.find { it.annotationClass == GraphQLName::class } as? GraphQLName)?.value ?: simpleName

fun Long.toID(clazz: KClass<out LongIdType>): ID? =
    clazz.graphqlTypeName?.let {
        ID(Relay().toGlobalId(it, "$this"))
    }

fun ID.getRawId(clazz: KClass<out LongIdType>): Long? {
    val (typeName, rawId) =
        try {
            Relay().fromGlobalId(this.value).let { it.type to it.id }
        } catch (e: Exception) {
            // todo logging
            return null
        }
    return if (typeName == clazz.graphqlTypeName) rawId.toLongOrNull() else null
}
