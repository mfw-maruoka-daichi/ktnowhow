package com.moneyforward.ktnowhow.model

sealed class ID {
    abstract val isDefined: Boolean
}

object UndefinedID : ID() {
    override val isDefined = false
}

data class DefinedID<out T>(val value: T) : ID() {
    override val isDefined = true
}
