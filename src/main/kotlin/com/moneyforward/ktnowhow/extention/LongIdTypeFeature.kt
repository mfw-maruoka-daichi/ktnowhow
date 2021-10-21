package com.moneyforward.ktnowhow.extention

import com.expediagroup.graphql.generator.annotations.GraphQLIgnore
import com.expediagroup.graphql.generator.scalars.ID
import com.moneyforward.ktnowhow.graphql.type.LongIdType
import kotlin.reflect.KClass

@GraphQLIgnore
interface LongIdTypeFeature {
    private val idDelimiter: String
        get() = ":"

    fun Long.toID(clazz: KClass<out LongIdType>): ID = ID("${clazz.simpleName}${idDelimiter}$this")
    fun ID.getRawId(clazz: KClass<out LongIdType>): Long? = value.split(idDelimiter).let {
        when {
            it.size != 2 -> null
            it.first() != clazz.simpleName -> null
            else -> it.last().toLongOrNull()
        }
    }
}
